package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.RegisterResponseDto;
import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.entity.Role;
import com.example.workintech.e_commerce.entity.User;
import com.example.workintech.e_commerce.repository.RoleRepository;
import com.example.workintech.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto register(String fullName, String email, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByCode("customer").get();

        User user = new User();
        user.setName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(userRole);

        User savedUser = userRepository.save(user);
       return new RegisterResponseDto(user.getId(), user.getEmail(), "User register successfully.");

    }

}
