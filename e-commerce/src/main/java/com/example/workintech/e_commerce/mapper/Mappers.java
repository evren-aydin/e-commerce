package com.example.workintech.e_commerce.mapper;


import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.entity.User;

public class Mappers {

    public static UserDto userToUserDto(User user){

        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(),user.getPassword());

        return userDto;

    }

    public static User userDtoToUser(UserDto userDto){

        User user = new User(userDto.getId(),userDto.getOrder(),userDto.getName(),userDto.getEmail(),userDto.getPassword(),userDto.getRole());

        return user;

    }


}
