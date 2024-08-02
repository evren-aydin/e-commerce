package com.example.workintech.e_commerce.repository;

import com.example.workintech.e_commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    @Query("SELECT c From Category c WHERE c.title=:title")
   Category findByTitle(String title);


}
