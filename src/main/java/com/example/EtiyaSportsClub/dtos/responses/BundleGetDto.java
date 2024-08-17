package com.example.EtiyaSportsClub.dtos.responses;

import com.example.EtiyaSportsClub.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundleGetDto {

    private Long bundleId;
    private Long userId;
    private String name;
    private String bundleName;
    private String bundleDescription;
    private double bundlePrice;
    private int totalLessonNumber;

}
