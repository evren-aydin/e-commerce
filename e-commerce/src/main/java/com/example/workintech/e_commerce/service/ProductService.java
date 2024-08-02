package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.ProductDto;
import com.example.workintech.e_commerce.dto.UserDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> fildAll();
    ProductDto findById(long id);

    ProductDto save(ProductDto productDto);
    ProductDto update(long id,ProductDto productDto);
    ProductDto delete(long id);
}
