package com.example.workintech.e_commerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponseDto {

    private long id;

    private String email;

    private String message;
}
