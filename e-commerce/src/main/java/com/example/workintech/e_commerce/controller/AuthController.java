package com.example.workintech.e_commerce.controller;

import com.example.workintech.e_commerce.dto.RegisterRequestDto;
import com.example.workintech.e_commerce.dto.RegisterResponseDto;
import com.example.workintech.e_commerce.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto){
    return authenticationService.register(registerRequestDto.getName(),registerRequestDto.getEmail(),registerRequestDto.getPassword());

    }
}
