
package com.daudu.libraryapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.daudu.libraryapi.domain.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>,
        PagingAndSortingRepository<UserEntity, Long> {
        Optional<UserEntity> findByEmail(String email);

}
