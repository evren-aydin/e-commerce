package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.CategoryDto;
import com.example.workintech.e_commerce.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> fildAll();
    OrderDto findById(long id);

    OrderDto save(OrderDto orderDto);
    OrderDto update(long id,OrderDto orderDto);
    OrderDto delete(long id);

}
