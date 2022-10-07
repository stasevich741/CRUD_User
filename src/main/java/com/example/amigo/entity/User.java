package com.example.amigo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonIgnoreProperties(value = {"firstName", "lastName"})
public class User {

    private final UUID userUid;

    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final Integer age;
    private final String email;

    public enum Gender {
        MALE, FEMALE
    }

    @JsonProperty("id")
    public UUID getUserUid() {
        return userUid;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public int getDateOfBirth() {
        return LocalDate.now().minusYears(age).getYear();
    }

    public User(@JsonProperty("userUid") UUID userUid
            , @JsonProperty("firstName") String firstName
            , @JsonProperty("lastName") String lastName
            , @JsonProperty("gender") Gender gender
            , @JsonProperty("age") Integer age
            , @JsonProperty("email") String email) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public static User newUser(UUID userUid, User user) {
        return User.builder()
                .userUid(userUid)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .age(user.getAge())
                .email(user.email)
                .build();
    }
}
