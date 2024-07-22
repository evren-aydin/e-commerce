package com.example.workintech.e_commerce.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {

    private LocalDateTime timestamp;

    private String message;

    private String path;

    private int errorCode;
}
