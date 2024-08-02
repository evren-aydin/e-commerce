package com.example.workintech.e_commerce.controller;

import com.example.workintech.e_commerce.dto.ProductDto;
import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.save(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<ProductDto> findAll(){
        return productService.fildAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable long id){
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable long id,@RequestBody ProductDto productDto){

        return productService.update(id,productDto);

    }

    @DeleteMapping("/{id}")
    public ProductDto delete(@PathVariable long id){
        return productService.delete(id);
    }

}
