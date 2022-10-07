package com.example.amigo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {

    @Setter
    private UUID userId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;
    private String email;

    public enum Gender {
        MALE, FEMALE
    }
}
