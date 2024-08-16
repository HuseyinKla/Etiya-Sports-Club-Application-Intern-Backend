package com.example.EtiyaSportsClub.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionGetDto {
    private String username;
    private String name;
    private String requestedRoleName;
}
