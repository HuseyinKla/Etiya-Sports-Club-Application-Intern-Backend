package com.example.EtiyaSportsClub.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundlesByUserId {
    private Long bundleId;
    private String bundleName;
    private int bundlePrice;
    private String bundleDescription;
    private int totalLessonNumber;
    private Timestamp purchaseDate;
}
