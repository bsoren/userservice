package com.bijay.userservice.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String userExistsWithGivenEmail) {
        super(userExistsWithGivenEmail);
    }
}
