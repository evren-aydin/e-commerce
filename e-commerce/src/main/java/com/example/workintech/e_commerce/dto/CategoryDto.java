package com.example.workintech.e_commerce.dto;
import com.example.workintech.e_commerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String code;
    private String title;
    private double rating;
    private char gender;
    private List<Product> products;

}
