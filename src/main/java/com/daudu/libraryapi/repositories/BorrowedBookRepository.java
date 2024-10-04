package com.daudu.libraryapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.daudu.libraryapi.domain.entities.BorrowedBookEntity;

@Repository
public interface BorrowedBookRepository extends CrudRepository<BorrowedBookEntity, Long>,
        PagingAndSortingRepository<BorrowedBookEntity, Long> {

}
