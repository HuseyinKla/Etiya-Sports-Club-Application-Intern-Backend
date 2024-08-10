package com.example.EtiyaSportsClub.dtos.requests;


import com.example.EtiyaSportsClub.entities.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundleWithCoursesDTO {

    private String username;
    private String bundleName;
    private String bundleDescription;
    private double bundlePrice;
    private int totalLessonNumber;
    private List<CourseEntity> courses;

}
