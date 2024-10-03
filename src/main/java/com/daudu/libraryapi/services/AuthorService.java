package com.daudu.libraryapi.services;

import java.util.List;

import com.daudu.libraryapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
