package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.LoginDto;
import com.example.EtiyaSportsClub.dtos.UserGetDto;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IUserGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import com.example.EtiyaSportsClub.repos.IRoleRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //add if coming data is not fit
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBundleRepository bundleRepository;
    @Autowired
    private IRoleRepository roleRepository;
    private RoleService roleService;


    public UserService(IUserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public List<UserGetDto> getAllUsersDto() {
        List<UserEntity> users = userRepository.findAll();
        return IUserGetMapper.INSTANCE.usersToGetAllUsersDto(users);
    }



    public UserGetDto findOneUserDto(Long userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            return IUserGetMapper.INSTANCE.userToGetUserDto(userOptional.get());
        }else{
            throw new RuntimeException("User not found");
        }
    }


    public UserEntity createNewUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }


    public UserEntity updateUser(Long userId, UserEntity newUser) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()){
            UserEntity foundedUser = user.get();
            foundedUser.setUserName(newUser.getUserName());
            foundedUser.setPassword(newUser.getPassword());
            userRepository.save(foundedUser);
            return foundedUser;
        }
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        Optional<UserEntity> foundedUser = userRepository.findByEmail(email);
        if (foundedUser.isPresent()){
            return foundedUser;
        }else{
            throw new RuntimeException("User Not Found");
        }
    }

    public UserEntity findUserByEmailPost(LoginDto loginDto) {
        Optional<UserEntity> foundedUser = userRepository.findByEmail(loginDto.getUsername());
        if (foundedUser.isPresent()){
            UserEntity user = foundedUser.get();
            return user;
        }else{
            throw new RuntimeException("User Not Found");
        }
    }
}
