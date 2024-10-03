package com.daudu.libraryapi.services;

import com.daudu.libraryapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);
}
