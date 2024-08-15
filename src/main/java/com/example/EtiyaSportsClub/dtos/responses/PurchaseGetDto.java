package com.example.EtiyaSportsClub.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseGetDto {

    private Long purchaseId;
    private Long userId;
    private String name;
    private Long bundleId;
    private String bundleName;
    private double bundlePrice;
    private int totalLessonNumber;
    private Timestamp purchaseDate;
}
