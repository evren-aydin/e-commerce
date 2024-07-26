package com.example.workintech.e_commerce.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{

    private final HttpStatus status;

    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }
}
