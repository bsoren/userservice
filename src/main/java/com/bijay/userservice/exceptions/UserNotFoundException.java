package com.bijay.userservice.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String invalidUser) {
        super(invalidUser);
    }
}
