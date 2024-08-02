package com.example.workintech.e_commerce.dto;

import com.example.workintech.e_commerce.entity.Category;
import com.example.workintech.e_commerce.entity.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;

    private String description;

    private double price;

    private int stock;

    private double rating;

    private int sell_count;

    @JsonProperty("category_id")
    private Long categoryId;


}
