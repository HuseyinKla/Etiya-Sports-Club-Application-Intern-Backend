package com.example.EtiyaSportsClub.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {

    private Long userId;
    private String userName;
    private String name;
    private String email;
    private String roleName;
}
