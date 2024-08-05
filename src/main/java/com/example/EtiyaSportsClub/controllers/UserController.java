package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.LoginDto;
import com.example.EtiyaSportsClub.dtos.UserGetDto;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserGetDto> getAllUsersDto(){
        return userService.getAllUsersDto();
    }

    @PostMapping
    public UserEntity createNewUser(@RequestBody UserEntity newUser){
        return userService.createNewUser(newUser);
    }

    @GetMapping("/email/{email}")
    public UserGetDto findUserByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }

    @PostMapping("/email")
    public UserGetDto findUserByUsernameAndPasswordPost(@RequestBody LoginDto loginDto){
        return userService.findUserByUsernameAndPasswordPost(loginDto);
    }


    @GetMapping("/{userId}")
    public UserGetDto findOneUserDto(@PathVariable Long userId){
        return userService.findOneUserDto(userId);
    }


    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId, @RequestBody UserEntity newUser){
        return userService.updateUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @DeleteMapping("/deleteAll")
    public void  deleteAllUser(){
        userService.deleteAllUser();
    }


}
