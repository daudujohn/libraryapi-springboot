package com.daudu.libraryapi.domain.dto;

import java.time.ZonedDateTime;

import com.daudu.libraryapi.domain.entities.BookEntity;
import com.daudu.libraryapi.domain.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalDto {

    private Long id;

    private UserEntity user;

    private BookEntity book;

    private ZonedDateTime loanDate;

    private ZonedDateTime returnDate;

    private boolean returned;

}