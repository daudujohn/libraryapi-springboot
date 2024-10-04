package com.daudu.libraryapi.domain.dto;

import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstname;

    private String lastname;

    private Set<BorrowedBookDto> borrowedBooks;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)  
    private Optional<String> googleId;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)  
    private String displayPicture;

}
