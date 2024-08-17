package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.entities.RoleEntity;
import com.example.EtiyaSportsClub.mappers.IRoleGetMapper;
import com.example.EtiyaSportsClub.repos.IRoleRepository;
import com.example.EtiyaSportsClub.dtos.responses.RoleGetDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    public List<RoleGetDto> getAllRolesDto() {
        List<RoleEntity> roles = roleRepository.findAll();
        return IRoleGetMapper.INSTANCE.rolesToGetAllRolesDto(roles);
    }

    public RoleEntity createNewRole(RoleEntity newRole) {
        return roleRepository.save(newRole);
    }




    public RoleGetDto getOneRoleDto(Long roleId) {
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        return IRoleGetMapper.INSTANCE.roleToGetRoleDto(role);
    }




    public RoleEntity updateRole(Long roleId, RoleEntity newRole) {
        Optional<RoleEntity> role = roleRepository.findById(roleId);
        if(role.isPresent()){
            RoleEntity foundedRole = role.get();
            foundedRole.setRoleName(newRole.getRoleName());
            foundedRole.setUserRoleNumber(newRole.getUserRoleNumber());
            roleRepository.save(foundedRole);
            return foundedRole;
        }
        return null;
    }

    public void deleteRole(Long roleId){
        roleRepository.deleteById(roleId);
    }

    public void deleteAllRole() {
        roleRepository.deleteAll();
    }
}

