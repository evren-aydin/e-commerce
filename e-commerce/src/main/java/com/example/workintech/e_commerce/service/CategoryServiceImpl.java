package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.CategoryDto;
import com.example.workintech.e_commerce.dto.ProductDto;
import com.example.workintech.e_commerce.entity.Category;
import com.example.workintech.e_commerce.entity.Product;
import com.example.workintech.e_commerce.exceptions.ResourceNotFoundException;
import com.example.workintech.e_commerce.mapper.Mappers;
import com.example.workintech.e_commerce.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public List<CategoryDto> fildAll() {
        return categoryRepository.findAll().stream().map((category)->Mappers.categoryToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()){
            return Mappers.categoryToCategoryDto(categoryOptional.get());

        }

        throw new ResourceNotFoundException("Category already exists with id: " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        if (categoryDto.getId() != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getId());
            if (categoryOptional.isPresent()) {
                throw new ResourceNotFoundException("Category already exists with id: " + categoryDto.getId(), HttpStatus.BAD_REQUEST);
            }
        }

        Category category = Mappers.categoryDtoToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return Mappers.categoryToCategoryDto(savedCategory);
    }


    @Override
    public CategoryDto update(long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id, HttpStatus.NOT_FOUND));
//optional degerin var olup olmadığı kontrolü yapılır. yoksa hata fırlatılır. Yani optional null kontrolü daha güvenli ve okunaklı şekilde yapılır.

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryDto.getId(), HttpStatus.NOT_FOUND));

        existingCategory.setCode(categoryDto.getCode());
        existingCategory.setTitle(categoryDto.getTitle());
        existingCategory.setRating(categoryDto.getRating());
        existingCategory.setGender(categoryDto.getGender());


        Category savedCategory = categoryRepository.save(existingCategory);
        return Mappers.categoryToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto delete(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id, HttpStatus.NOT_FOUND));
        CategoryDto categoryDto = Mappers.categoryToCategoryDto(category);
        categoryRepository.delete(category);
        return categoryDto;
    }
}
