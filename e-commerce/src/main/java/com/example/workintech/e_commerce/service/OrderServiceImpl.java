package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.CategoryDto;
import com.example.workintech.e_commerce.dto.OrderDto;
import com.example.workintech.e_commerce.entity.Category;
import com.example.workintech.e_commerce.entity.Order;
import com.example.workintech.e_commerce.entity.Product;
import com.example.workintech.e_commerce.entity.User;
import com.example.workintech.e_commerce.exceptions.ResourceNotFoundException;
import com.example.workintech.e_commerce.mapper.Mappers;
import com.example.workintech.e_commerce.repository.OrderRepository;
import com.example.workintech.e_commerce.repository.ProductRepository;
import com.example.workintech.e_commerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderDto> fildAll() {
        return orderRepository.findAll().stream().map((order)->Mappers.orderToOrderDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()){
            return Mappers.orderToOrderDto(orderOptional.get());

        }

        throw new ResourceNotFoundException("Order already exists with id: " + id, HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public OrderDto save(OrderDto orderDto) {
        if (orderDto.getId() != null) {
            Optional<Order> orderOptional = orderRepository.findById(orderDto.getId());
            if (orderOptional.isPresent()) {
                throw new ResourceNotFoundException("Order already exists with id: " + orderDto.getId(), HttpStatus.BAD_REQUEST);
            }
        }

        // User'ı al
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderDto.getUserId(), HttpStatus.NOT_FOUND));

        // Product listesi oluştur
        List<Product> products = productRepository.findAllById(orderDto.getProductIds());
        if (products.size() != orderDto.getProductIds().size()) {
            throw new ResourceNotFoundException("Some products not found", HttpStatus.NOT_FOUND);
        }

        Order order = Mappers.orderDtoToOrder(orderDto, user, products);
        Order savedOrder = orderRepository.save(order);

        return Mappers.orderToOrderDto(savedOrder);
    }


    @Transactional
    @Override
    public OrderDto update(long id, OrderDto orderDto) {
        // Order var mı kontrol et
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id, HttpStatus.NOT_FOUND));

        // Kullanıcıyı al
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderDto.getUserId(), HttpStatus.NOT_FOUND));

        // Ürünleri al
        List<Product> products = orderDto.getProductIds().stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId, HttpStatus.NOT_FOUND)))
                .collect(Collectors.toList());

        // Order'ı güncelle
        existingOrder.setUser(user);
        existingOrder.setOrderDate(orderDto.getOrderDate());
        existingOrder.setPrice(orderDto.getPrice());
        existingOrder.setProducts(products);

        // Kaydet ve DTO'ya dönüştür
        Order updatedOrder = orderRepository.save(existingOrder);
        return Mappers.orderToOrderDto(updatedOrder);
    }

    @Override
    public OrderDto delete(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id, HttpStatus.NOT_FOUND));
        OrderDto orderDto = Mappers.orderToOrderDto(order);
        orderRepository.delete(order);
        return orderDto;
    }
}
