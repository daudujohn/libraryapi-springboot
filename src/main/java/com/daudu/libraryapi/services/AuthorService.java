package com.daudu.libraryapi.services;

import com.daudu.libraryapi.domain.dto.AuthorDto;
import com.daudu.libraryapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorDto author);
}
