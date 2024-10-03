package com.daudu.libraryapi.services.impl;

import org.springframework.stereotype.Service;

import com.daudu.libraryapi.domain.entities.AuthorEntity;
import com.daudu.libraryapi.repositories.AuthorRepository;
import com.daudu.libraryapi.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}