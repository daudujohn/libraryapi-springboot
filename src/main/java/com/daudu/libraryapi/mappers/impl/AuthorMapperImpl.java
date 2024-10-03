package com.daudu.libraryapi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.daudu.libraryapi.domain.dto.AuthorDto;
import com.daudu.libraryapi.domain.entities.AuthorEntity;
import com.daudu.libraryapi.mappers.Mapper;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

    private ModelMapper modelMapper;

    private AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

}
