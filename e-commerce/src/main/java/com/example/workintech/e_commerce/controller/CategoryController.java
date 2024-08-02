package com.example.workintech.e_commerce.controller;

import com.example.workintech.e_commerce.dto.CategoryDto;
import com.example.workintech.e_commerce.dto.ProductDto;
import com.example.workintech.e_commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedProduct = categoryService.save(categoryDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<CategoryDto> findAll(){
        return categoryService.fildAll();
    }
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable long id){
        return categoryService.findById(id);
    }
    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable long id,@RequestBody CategoryDto categoryDto){

        return categoryService.update(id,categoryDto);

    }
    @DeleteMapping("/{id}")
    public CategoryDto delete(@PathVariable long id){
        return categoryService.delete(id);
    }

}
