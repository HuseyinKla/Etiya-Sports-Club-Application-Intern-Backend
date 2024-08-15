package com.example.EtiyaSportsClub.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String name;
    private String username;
    private String email;
    private String password;
    private String rolename;

}
