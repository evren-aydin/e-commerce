package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> fildAll();
    UserDto findById(long id);

    UserDto save(UserDto userDto);
    UserDto update(long id,UserDto userDto);
    UserDto delete(long id);


}
