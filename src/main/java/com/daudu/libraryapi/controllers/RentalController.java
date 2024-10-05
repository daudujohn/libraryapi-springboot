package com.daudu.libraryapi.controllers;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daudu.libraryapi.domain.dto.RentalDto;
import com.daudu.libraryapi.domain.entities.RentalEntity;
import com.daudu.libraryapi.domain.entities.BookEntity;
import com.daudu.libraryapi.mappers.Mapper;
import com.daudu.libraryapi.services.BookService;
import com.daudu.libraryapi.services.RentalService;
import com.daudu.libraryapi.services.UserService;

@RestController
public class RentalController {

    private RentalService rentalService;

    private Mapper<RentalEntity, RentalDto> rentalMapper;

    private BookService bookService;

    private UserService userService;

    public RentalController(Mapper<RentalEntity, RentalDto> rentalMapper,
            RentalService rentalService, BookService bookService, UserService userService) {
        this.rentalMapper = rentalMapper;
        this.rentalService = rentalService;
        this.bookService = bookService;
        this.userService = userService;

    }

    @GetMapping(path = "/rentals")
    public Page<RentalDto> listRentals(Pageable pageable) {
        Page<RentalEntity> rentals = rentalService.findAll(pageable);
        return rentals.map(rentalMapper::mapTo);
    }

    @GetMapping(path = "/rentals/{rental_id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable("rental_id") Long rental_id) {
        Optional<RentalEntity> foundRental = rentalService.findOne(rental_id);
        return foundRental.map(rentalEntity -> {
            RentalDto rentalDto = rentalMapper.mapTo(rentalEntity);
            return new ResponseEntity<>(rentalDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/books/{isbn}/user/{user_id}")
    public ResponseEntity<RentalDto> rentBook(@PathVariable("user_id") Long user_id,
            @PathVariable("isbn") String isbn) {
        if (!bookService.isExists(isbn) || !userService.isExists(user_id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookService.findOne(isbn).get();
        if (bookEntity.getStock() > 0) {
            bookEntity.setStock(bookEntity.getStock() - 1);
            bookService.partialUpdate(isbn, bookEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RentalDto rentalDto = new RentalDto();
        rentalDto.getUser().setId(user_id);
        rentalDto.getBook().setIsbn(isbn);

        ZonedDateTime loanDate = java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC);
        rentalDto.setLoanDate(loanDate);
        rentalDto.setReturnDate(loanDate.plusWeeks(2));

        RentalEntity rentalEntity = rentalMapper.mapFrom(rentalDto);
        RentalEntity savedRentalEntity = rentalService.save(rentalEntity);
        return new ResponseEntity<>(rentalMapper.mapTo(savedRentalEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/rentals/{rental_id}/return")
    public ResponseEntity<RentalDto> returnBook(@PathVariable("rental_id") Long rental_id) {
        if (!rentalService.isExists(rental_id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RentalEntity rentalEntity = rentalService.findOne(rental_id).get();
        rentalEntity.setReturned(true);
        rentalService.partialUpdate(rental_id, rentalEntity);

        String isbn = rentalEntity.getBook().getIsbn();
        Optional<BookEntity> optionalBookEntity = bookService.findOne(isbn);
        if (optionalBookEntity.isPresent()) {
            BookEntity bookEntity = optionalBookEntity.get();
            bookEntity.setStock(bookEntity.getStock() + 1);
            bookService.partialUpdate(isbn, bookEntity);
        }

        return new ResponseEntity<>(rentalMapper.mapTo(rentalEntity), HttpStatus.OK);
    }

}
