package com.example.amigo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

//@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

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
