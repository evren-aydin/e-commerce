package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.CategoryDto;
import com.example.workintech.e_commerce.dto.ProductDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> fildAll();
    CategoryDto findById(long id);

    CategoryDto save(CategoryDto categoryDto);
    CategoryDto update(long id,CategoryDto categoryDto);
    CategoryDto delete(long id);

}
