package com.example.workintech.e_commerce.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetailsWithValidation {

    private LocalDateTime timestamp;

    private String path;
    private int errorCode;

    private Map<String,String> message;
}
