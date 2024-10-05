package com.daudu.libraryapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.daudu.libraryapi.domain.entities.RentalEntity;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Long>,
        PagingAndSortingRepository<RentalEntity, Long> {

}
