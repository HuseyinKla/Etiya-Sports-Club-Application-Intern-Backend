package com.example.EtiyaSportsClub.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleGetDto {

    private Long roleId;
    private String roleName;

}
