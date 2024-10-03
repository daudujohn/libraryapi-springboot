package com.daudu.libraryapi.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.daudu.libraryapi.domain.entities.BookEntity;
import com.daudu.libraryapi.repositories.BookRepository;
import com.daudu.libraryapi.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

}
