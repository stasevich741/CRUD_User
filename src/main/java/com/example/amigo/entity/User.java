package com.example.amigo.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

//@Value
//@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

  private final UUID userId;
  private final String firstName;
  private final String lastName;
  private final Gender gender;
  private final Integer age;
  private final String email;

   public enum Gender {
        MALE, FEMALE
    }
}
