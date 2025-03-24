package com.bijay.userservice.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String invalidPassword) {
        super(invalidPassword);
    }
}
