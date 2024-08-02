package com.example.workintech.e_commerce.dto;

import com.example.workintech.e_commerce.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {

    private Long id;

    private String name;

    private String code;

    private List<User> users;
}
