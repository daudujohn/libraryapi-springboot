package com.daudu.libraryapi.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daudu.libraryapi.domain.dto.BookDto;
import com.daudu.libraryapi.domain.entities.BookEntity;
import com.daudu.libraryapi.mappers.Mapper;
import com.daudu.libraryapi.services.BookService;

@RestController
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);

        if (bookExists) {
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books")
    public Page<BookDto> listBooks(Pageable pageable) {
        Page<BookEntity> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
