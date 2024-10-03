package com.daudu.libraryapi.services;

import java.util.List;

import com.daudu.libraryapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();
}
