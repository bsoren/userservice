package com.bijay.userservice.services;

import com.bijay.userservice.exceptions.InvalidPasswordException;
import com.bijay.userservice.exceptions.UserAlreadyExistException;
import com.bijay.userservice.exceptions.UserNotFoundException;
import com.bijay.userservice.models.User;
import com.bijay.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCrypt;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCrypt) {
        this.userRepository = userRepository;
        this.bCrypt = bCrypt;
    }

    @Override
    public User createUser(User user) {
        Optional<User> userByEmail = this.userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistException("User exists with given email");
        }

        user.setPassword(bCrypt.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Invalid User"));

        if (!bCrypt.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        return user;
    }

    @Override
    public void logout(String email) {

    }
}
