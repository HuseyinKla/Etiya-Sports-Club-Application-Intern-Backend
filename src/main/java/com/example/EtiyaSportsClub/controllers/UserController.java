package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.requests.LoginDto;
import com.example.EtiyaSportsClub.dtos.requests.UserCreateDto;
import com.example.EtiyaSportsClub.dtos.responses.UserDontHaveBundleDto;
import com.example.EtiyaSportsClub.dtos.responses.UserGetDto;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.repos.IRoleRepository;
import com.example.EtiyaSportsClub.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final  UserService userService;
    private final  IRoleRepository roleRepository;

    public UserController(UserService userService, IRoleRepository roleRepository){
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<UserGetDto> getAllUsersDto(){
        return userService.getAllUsersDto();
    }

    @GetMapping("/withoutPurchases")
    public List<UserDontHaveBundleDto> getUsersWithoutPurchases() {
        return userService.getUsersWithoutPurchases();
    }


    @PostMapping
    public UserEntity createNewUser(@RequestBody UserCreateDto newUser){
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
