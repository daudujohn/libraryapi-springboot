package com.daudu.libraryapi.services;

import java.util.List;
import java.util.Optional;

import com.daudu.libraryapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);
}
