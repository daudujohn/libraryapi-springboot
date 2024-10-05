package com.daudu.libraryapi.domain.dto;

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
public class BookDto {

    private String isbn;

    private String title;

    private AuthorDto author;

    private Set<RentalDto> rentals;

    private Integer stock;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String coverImage;

}
