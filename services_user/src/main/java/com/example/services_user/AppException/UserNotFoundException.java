package com.example.services_user.AppException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User Id: " + id + " not found!");
    }
}
