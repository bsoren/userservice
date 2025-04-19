package com.bijay.userservice.security;

import com.bijay.userservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.bijay.userservice.models.User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        User user1 = new User(
                user.getEmail(),
                user.getPassword(),
                Stream.of("USER")
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        return user1;
    }
}
