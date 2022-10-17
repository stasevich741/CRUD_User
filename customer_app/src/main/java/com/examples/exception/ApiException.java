package com.examples.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


//what will client going to see
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@ToString
public class ApiException {

    String message;
    Throwable throwable;
    HttpStatus httpStatus;
    ZonedDateTime zonedDateTime;
}
