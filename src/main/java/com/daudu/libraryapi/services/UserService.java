package com.daudu.libraryapi.services;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daudu.libraryapi.domain.entities.UserEntity;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    List<UserEntity> findAll();

    Page<UserEntity> findAll(Pageable pageable);

    Optional<UserEntity> findOne(Long id);

    Optional<UserEntity> findByEmail(String email);

    boolean isExists(Long id);

    UserEntity partialUpdate(Long id, UserEntity userEntity);

    void delete(Long id);
}
