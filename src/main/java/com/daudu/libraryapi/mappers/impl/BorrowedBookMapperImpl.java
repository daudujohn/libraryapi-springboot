package com.daudu.libraryapi.mappers.impl;

import org.modelmapper.ModelMapper;

import com.daudu.libraryapi.domain.dto.BorrowedBookDto;
import com.daudu.libraryapi.domain.entities.BorrowedBookEntity;
import com.daudu.libraryapi.mappers.Mapper;

public class BorrowedBookMapperImpl implements Mapper<BorrowedBookEntity, BorrowedBookDto> {

    private ModelMapper modelMapper;

    public BorrowedBookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowedBookEntity mapFrom(BorrowedBookDto borrowedBookDto) {
        return modelMapper.map(borrowedBookDto, BorrowedBookEntity.class);
    }

    @Override
    public BorrowedBookDto mapTo(BorrowedBookEntity borrowedBookEntity) {
        return modelMapper.map(borrowedBookEntity, BorrowedBookDto.class);
    }

}
