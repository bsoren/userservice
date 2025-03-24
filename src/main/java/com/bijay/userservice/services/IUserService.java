package com.bijay.userservice.services;


import com.bijay.userservice.models.User;

public interface IUserService {
    User createUser(User user);
    User login(String email, String password);
    void logout(String email);

}
