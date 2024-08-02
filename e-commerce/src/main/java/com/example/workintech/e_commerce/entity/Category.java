package com.example.workintech.e_commerce.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="category",schema = "workintech")
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="code")
    private String code;
    @Column(name="title")
    private String title;
    @Column(name="rating")
    private double rating;
    @Column(name="gender")
    private char gender;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "category")
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();


}
