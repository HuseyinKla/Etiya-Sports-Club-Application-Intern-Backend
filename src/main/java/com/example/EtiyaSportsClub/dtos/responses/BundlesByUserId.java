package com.example.EtiyaSportsClub.dtos.responses;


import com.example.EtiyaSportsClub.entities.ProgressEntity;
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
    private double bundlePrice;
    private String bundleDescription;
    private int totalLessonNumber;
    private int remainingCourseNumber;
    private Timestamp purchaseDate;
    private ProgressEntity.processStatus processStatus;
}
