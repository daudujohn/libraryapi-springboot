package com.daudu.libraryapi.services.impl;

import org.springframework.stereotype.Service;

import com.daudu.libraryapi.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

}
