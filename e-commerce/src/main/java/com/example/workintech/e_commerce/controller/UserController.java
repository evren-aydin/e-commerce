package com.example.workintech.e_commerce.controller;

import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.entity.User;
import com.example.workintech.e_commerce.mapper.Mappers;
import com.example.workintech.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public UserDto save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable long id,@RequestBody UserDto userDto){

        return userService.update(id,userDto);

    }

    @GetMapping("/")
    public List<UserDto> findAll(){
        return userService.fildAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable long id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable long id){
        return userService.delete(id);
    }
}
