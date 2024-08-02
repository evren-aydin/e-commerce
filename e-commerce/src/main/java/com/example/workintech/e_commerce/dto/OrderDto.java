package com.example.workintech.e_commerce.dto;

import com.example.workintech.e_commerce.entity.Product;
import com.example.workintech.e_commerce.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private LocalDateTime orderDate;

    private double price;

    @JsonProperty("product_ids")
    private List<Long> productIds;
}
