package com.example.workintech.e_commerce.repository;

import com.example.workintech.e_commerce.entity.Role;
import com.example.workintech.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r From Role r WHERE r.code=:code")
    Optional<Role> findByCode(String code);
}
