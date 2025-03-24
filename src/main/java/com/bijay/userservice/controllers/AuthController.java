package com.bijay.userservice.controllers;

import com.bijay.userservice.dtos.LoginRequestDto;
import com.bijay.userservice.dtos.SignUpRequestDto;
import com.bijay.userservice.dtos.UserDto;
import com.bijay.userservice.models.User;
import com.bijay.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = this.userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return from(user);
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = this.userService.createUser(this.from(signUpRequestDto));
        return from(user);
    }

    @PostMapping("/logout/{email}")
    public String logout(@PathVariable String email) {
        return "logout";
    }

    public UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public User from(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());
        return user;
    }

}
