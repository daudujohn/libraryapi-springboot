package com.daudu.libraryapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daudu.libraryapi.domain.entities.RentalEntity;

public interface RentalService {
    RentalEntity save(RentalEntity rentalEntity);

    List<RentalEntity> findAll();

    Page<RentalEntity> findAll(Pageable pageable);

    Optional<RentalEntity> findOne(Long rentalId);

    boolean isExists(Long rentalId);

    RentalEntity partialUpdate(Long rentalId, RentalEntity rentalEntity);

    void delete(Long rentalId);
}
