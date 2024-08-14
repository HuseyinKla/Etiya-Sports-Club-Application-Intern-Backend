package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.RoleGetDto;
import com.example.EtiyaSportsClub.entities.RoleEntity;
import com.example.EtiyaSportsClub.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;


    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleGetDto> getAllRolesDto(){
        return roleService.getAllRolesDto();
    }



    @GetMapping("/{roleId}")
    public RoleGetDto getOneRoleDto(@PathVariable Long roleId){
        return roleService.getOneRoleDto(roleId);
    }



    @PutMapping("/{roleId}")
    public RoleEntity updateRole(@PathVariable Long roleId, @RequestBody RoleEntity newRole){
        return roleService.updateRole(roleId, newRole);
    }

    @PostMapping
    public RoleEntity createNewRole(@RequestBody RoleEntity newRole){
        return roleService.createNewRole(newRole);
    }

    @DeleteMapping("/{roleId}")
    public void deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllRole(){
        roleService.deleteAllRole();
    }


}
