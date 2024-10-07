package com.daudu.libraryapi.domain.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;

    private String firstname;

    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<RentalEntity> borrowedBooks;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String googleId;

    @Column(nullable = true)
    private String displayPicture;

}