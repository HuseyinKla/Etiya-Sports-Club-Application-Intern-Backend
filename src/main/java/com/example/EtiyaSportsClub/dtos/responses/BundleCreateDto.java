package com.example.EtiyaSportsClub.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundleCreateDto {

    private String bundleName;
    private String bundleDescription;
    private double bundlePrice;
    private int totalLessonNumber;
    private List<CourseCreateDto> courses;

}
