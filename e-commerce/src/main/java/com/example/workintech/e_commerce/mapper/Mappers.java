package com.example.workintech.e_commerce.mapper;


import com.example.workintech.e_commerce.dto.*;
import com.example.workintech.e_commerce.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Mappers {

    public static UserDto userToUserDto(User user){

        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(),user.getPassword());

        return userDto;

    }

    public static User userDtoToUser(UserDto userDto){

        User user = new User(userDto.getId(),userDto.getOrders(),userDto.getName(),userDto.getEmail(),userDto.getPassword(),userDto.getRole());

        return user;

    }
    public static RoleDto RoleToRoleDto(Role role){

        RoleDto roleDto = new RoleDto(role.getId(),role.getName(),role.getCode(),role.getUsers());

        return roleDto;

    }

    public static Role roleDtoToRole(RoleDto roleDto){

        Role role = new Role(roleDto.getId(), roleDto.getName(), roleDto.getCode(), roleDto.getUsers());
        return role;

    }
    public static CategoryDto categoryToCategoryDto(Category category){

        CategoryDto categoryDto = new CategoryDto(category.getId(),category.getCode(),category.getTitle(),category.getRating(),category.getGender(),category.getProducts());

        return categoryDto;

    }

    public static Category categoryDtoToCategory(CategoryDto categoryDto){

        Category category = new Category(categoryDto.getId(), categoryDto.getCode(), categoryDto.getTitle(), categoryDto.getRating(), categoryDto.getGender(),categoryDto.getProducts());

        return category;

    }
    public static OrderDto orderToOrderDto(Order order){
        List<Long> productIds = order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        return new OrderDto(order.getId(), order.getUser().getId(), order.getOrderDate(), order.getPrice(), productIds);
    }

    public static Order orderDtoToOrder(OrderDto orderDto, User user, List<Product> products){
        return new Order(orderDto.getId(), user, orderDto.getOrderDate(), orderDto.getPrice(), products);
    }
    public static ProductDto productToProductDto(Product product){


        ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getRating(), product.getSell_count(),product.getCategory().getId()
                );

        return productDto;

    }

    public static Product productDtoToProduct(ProductDto productDto, Category category) {

        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setRating(productDto.getRating());
        product.setSell_count(productDto.getSell_count());
        product.setCategory(category);

        // Eğer orders DTO'dan gelecekse, bunu da ayarlamanız gerekebilir
        // Ancak genellikle, orders'ı yeni oluşturulacaksa ayrı bir işlemle ekleriz

        return product;

    }

}
