package com.example.workintech.e_commerce.repository;

import com.example.workintech.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
