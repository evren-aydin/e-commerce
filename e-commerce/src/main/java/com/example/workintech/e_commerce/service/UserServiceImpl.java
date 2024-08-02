package com.example.workintech.e_commerce.service;

import com.example.workintech.e_commerce.dto.UserDto;
import com.example.workintech.e_commerce.entity.Role;
import com.example.workintech.e_commerce.entity.User;
import com.example.workintech.e_commerce.exceptions.ResourceNotFoundException;
import com.example.workintech.e_commerce.mapper.Mappers;
import com.example.workintech.e_commerce.repository.RoleRepository;
import com.example.workintech.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->{
                    System.out.println("User credentials are not valid");
                    throw new UsernameNotFoundException("User credentials are not valid");
                });
    }

    @Override
    public List<UserDto> fildAll() {

    return userRepository.findAll().stream().map((user)->Mappers.userToUserDto(user)).collect(Collectors.toList());

    }

    @Override
    public UserDto findById(long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            return Mappers.userToUserDto(userOptional.get());

        }
        //TODO[Evren]: Buraya Exception Handling ekle.
        return null;
    }

    @Override
    public UserDto save(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if (userOptional.isPresent()) {
            throw new ResourceNotFoundException("User already exists with email: " + userDto.getEmail(), HttpStatus.BAD_REQUEST);
        }
        User user= Mappers.userDtoToUser(userDto);
        // role_id kontrolü ve Role nesnesini almak
        if (user.getRole() == null || user.getRole().getId() == null) {
            throw new ResourceNotFoundException("Role ID must be provided", HttpStatus.BAD_REQUEST);
        }

        Long roleId = user.getRole().getId();
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));

        user.setRole(role);

        User savedUser= userRepository.save(user);
        return Mappers.userToUserDto(savedUser);
    }

    @Override
    public UserDto update(long id, UserDto userDto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND));

        Long roleId = userDto.getRole() != null ? userDto.getRole().getId() : null;
        if (roleId != null) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId, HttpStatus.NOT_FOUND));
            existingUser.setRole(role);
        }

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        User savedUser = userRepository.save(existingUser);
        return Mappers.userToUserDto(savedUser);
    }

    @Override
    public UserDto delete(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND));
        UserDto userDto = Mappers.userToUserDto(user);
        userRepository.delete(user);
        return userDto;
    }
}
