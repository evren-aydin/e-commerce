package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.ProductDto;
import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.entity.Category;
import com.example.workintech.e_commerce.entity.Product;
import com.example.workintech.e_commerce.entity.Role;
import com.example.workintech.e_commerce.entity.User;
import com.example.workintech.e_commerce.exceptions.ResourceNotFoundException;
import com.example.workintech.e_commerce.mapper.Mappers;
import com.example.workintech.e_commerce.repository.CategoryRepository;
import com.example.workintech.e_commerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> fildAll() {

        return productRepository.findAll().stream().map((product)->Mappers.productToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()){
            return Mappers.productToProductDto(productOptional.get());

        }
        //TODO[Evren]: Buraya Exception Handling ekle.
        return null;
    }

    @Transactional
    @Override
    public ProductDto save(ProductDto productDto) {

        if (productDto.getId() != null) {
            Optional<Product> productOptional = productRepository.findById(productDto.getId());
            if (productOptional.isPresent()) {
                throw new ResourceNotFoundException("Product already exists with id: " + productDto.getId(), HttpStatus.BAD_REQUEST);
            }
        }
        if (productDto.getCategoryId() == null) {
            throw new ResourceNotFoundException("Category ID must be provided", HttpStatus.BAD_REQUEST);
        }
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDto.getCategoryId(), HttpStatus.NOT_FOUND));


        Product product = Mappers.productDtoToProduct(productDto, category);
        Product savedProduct = productRepository.save(product);
        return Mappers.productToProductDto(savedProduct);
    }

    @Override
    public ProductDto update(long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
    //ürün bulunursa optional nesnesi Product atanır bulunamazsa hata throw eder.
        if (productDto.getCategoryId() == null) {
            throw new ResourceNotFoundException("Category ID must be provided", HttpStatus.BAD_REQUEST);
        }
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDto.getCategoryId(), HttpStatus.NOT_FOUND));

        existingProduct.setName(productDto.getName());
        existingProduct.setSell_count(productDto.getSell_count());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setRating(productDto.getRating());
        existingProduct.setDescription(productDto.getDescription());

        Product savedProduct = productRepository.save(existingProduct);
        return Mappers.productToProductDto(savedProduct);
    }

    @Override
    public ProductDto delete(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        ProductDto productDto = Mappers.productToProductDto(product);
        productRepository.delete(product);
        return productDto;
    }
}
