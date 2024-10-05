package com.daudu.libraryapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.daudu.libraryapi.domain.entities.RentalEntity;
import com.daudu.libraryapi.repositories.RentalRepository;
import com.daudu.libraryapi.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public RentalEntity save(RentalEntity rentalEntity) {
        return rentalRepository.save(rentalEntity);
    }

    @Override
    public List<RentalEntity> findAll() {
        return StreamSupport.stream(rentalRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RentalEntity> findAll(Pageable pageable) {
        return rentalRepository.findAll(pageable);
    }

    @Override
    public Optional<RentalEntity> findOne(Long rentalId) {
        return rentalRepository.findById(rentalId);
    }

    @Override
    public boolean isExists(Long rentalId) {
        return rentalRepository.existsById(rentalId);
    }

    @Override
    public RentalEntity partialUpdate(Long rentalId, RentalEntity rentalEntity) {
        rentalEntity.setId(rentalId);

        return rentalRepository.findById(rentalId).map(existingBorrowedBook -> {
            Optional.ofNullable(rentalEntity.getLoanDate()).ifPresent(existingBorrowedBook::setLoanDate);
            Optional.ofNullable(rentalEntity.getReturnDate()).ifPresent(existingBorrowedBook::setReturnDate);
            return rentalRepository.save(existingBorrowedBook);
        }).orElseThrow(() -> new RuntimeException("Borrowed record does not exist"));

    }

    @Override
    public void delete(Long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

}
