package com.daudu.libraryapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daudu.libraryapi.domain.entities.UserEntity;
import com.daudu.libraryapi.repositories.UserRepository;
import com.daudu.libraryapi.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity bookEntity) {
        return userRepository.save(bookEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity partialUpdate(Long id, UserEntity userEntity) {
        userEntity.setId(id);

        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userEntity.getFirstname()).ifPresent(existingUser::setFirstname);
            Optional.ofNullable(userEntity.getLastname()).ifPresent(existingUser::setLastname);
            Optional.ofNullable(userEntity.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(userEntity.getGoogleId()).ifPresent(existingUser::setGoogleId);
            Optional.ofNullable(userEntity.getDisplayPicture()).ifPresent(existingUser::setDisplayPicture);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));

    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
