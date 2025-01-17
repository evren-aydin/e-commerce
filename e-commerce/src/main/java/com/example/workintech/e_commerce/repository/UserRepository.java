package com.example.workintech.e_commerce.repository;

import com.example.workintech.e_commerce.entity.Category;
import com.example.workintech.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u From User u WHERE u.email=:email")
    Optional<User> findByEmail(String email);
}
