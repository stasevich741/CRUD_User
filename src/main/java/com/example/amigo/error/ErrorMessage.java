package com.example.amigo.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String ErrorMessage;

    public ErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }
}
