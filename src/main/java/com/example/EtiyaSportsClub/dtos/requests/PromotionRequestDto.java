package com.example.EtiyaSportsClub.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequestDto {
    private String username;
    private String requestedRoleName;
}
