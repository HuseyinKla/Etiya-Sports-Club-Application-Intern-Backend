package com.example.EtiyaSportsClub.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyBundleDto {

    private String username;
    private Long bundleId;
    private Timestamp purchaseDate;



}
