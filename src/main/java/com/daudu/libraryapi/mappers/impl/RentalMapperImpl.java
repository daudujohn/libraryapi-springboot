package com.daudu.libraryapi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.daudu.libraryapi.domain.dto.RentalDto;
import com.daudu.libraryapi.domain.entities.RentalEntity;
import com.daudu.libraryapi.mappers.Mapper;

@Component
public class RentalMapperImpl implements Mapper<RentalEntity, RentalDto> {

    private ModelMapper modelMapper;

    public RentalMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RentalEntity mapFrom(RentalDto rentalDto) {
        return modelMapper.map(rentalDto, RentalEntity.class);
    }

    @Override
    public RentalDto mapTo(RentalEntity rentalEntity) {
        return modelMapper.map(rentalEntity, RentalDto.class);
    }

}
