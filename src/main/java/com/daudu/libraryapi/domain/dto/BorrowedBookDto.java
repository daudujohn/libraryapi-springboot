package com.daudu.libraryapi.domain.dto;

import java.time.LocalDate;

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
public class BorrowedBookDto {

    private Long id;

    private UserEntity user;

    private BookEntity book;

    private LocalDate loanDate;

    private LocalDate returnDate;
}