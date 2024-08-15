package com.example.EtiyaSportsClub.mappers;


import com.example.EtiyaSportsClub.dtos.responses.UserGetDto;
import com.example.EtiyaSportsClub.dtos.responses.UserDontHaveBundleDto;
import com.example.EtiyaSportsClub.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IUserGetMapper {

    IUserGetMapper INSTANCE = Mappers.getMapper(IUserGetMapper.class);

    @Mapping(source = "role.roleName", target = "roleName")
    UserGetDto userToGetUserDto(UserEntity user);
    List<UserGetDto> usersToGetAllUsersDto(List<UserEntity> users);


    List<UserDontHaveBundleDto> usersToUsersDontHaveBundle(List<UserEntity> users);

}
