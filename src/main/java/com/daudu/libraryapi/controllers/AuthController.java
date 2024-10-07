package com.daudu.libraryapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daudu.libraryapi.domain.dto.UserDto;
import com.daudu.libraryapi.domain.entities.UserEntity;
import com.daudu.libraryapi.mappers.Mapper;
import com.daudu.libraryapi.services.UserService;

@RestController
public class AuthController {

    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder;

    private Mapper<UserEntity, UserDto> userMapper;

    @Autowired
    public AuthController(UserService userService,
            Mapper<UserEntity, UserDto> userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        System.out.println("\n\nHello, World!\n\n");
        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userService.save(userEntity);

        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        Optional<UserEntity> possibleUser = userService.findByEmail(userDto.getEmail());

        if (!possibleUser.isPresent()) {
            return new ResponseEntity<>("Invalid email or password",
                    HttpStatus.UNAUTHORIZED);
        }

        UserEntity existingUser = possibleUser.get();
        if (existingUser == null || !passwordEncoder.matches(userDto.getPassword(),
                existingUser.getPassword())) {
            return new ResponseEntity<>("Invalid email jor password",
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

}
