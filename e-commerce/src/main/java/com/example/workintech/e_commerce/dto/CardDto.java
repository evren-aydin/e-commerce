package com.example.workintech.e_commerce.dto;

public record CardDto(

        long id,
        String cardNumber,
        int expireMonth,
        int expireYear,
        String nameOnCard
) {
}
