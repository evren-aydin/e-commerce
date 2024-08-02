package com.example.workintech.e_commerce.controller;

import com.example.workintech.e_commerce.dto.CategoryDto;
import com.example.workintech.e_commerce.dto.OrderDto;
import com.example.workintech.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.save(orderDto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public List<OrderDto> findAll(){
        return orderService.fildAll();
    }
    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable long id){
        return orderService.findById(id);
    }
    @PutMapping("/{id}")
    public OrderDto update(@PathVariable long id,@RequestBody OrderDto orderDto){

        return orderService.update(id,orderDto);

    }

    @DeleteMapping("/{id}")
    public OrderDto delete(@PathVariable long id){
        return orderService.delete(id);
    }

}
