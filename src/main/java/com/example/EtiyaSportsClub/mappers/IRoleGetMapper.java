package com.example.EtiyaSportsClub.mappers;


import com.example.EtiyaSportsClub.entities.RoleEntity;
import org.mapstruct.Mapper;
import com.example.EtiyaSportsClub.dtos.responses.RoleGetDto;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IRoleGetMapper {

    IRoleGetMapper INSTANCE = Mappers.getMapper(IRoleGetMapper.class);

    RoleGetDto roleToGetRoleDto(RoleEntity role);
    List<RoleGetDto> rolesToGetAllRolesDto(List<RoleEntity> roles);
}
