package com.example.EtiyaSportsClub.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesByBundleIdDto {

    private Long bundleId;
    private String bundleName;
    private String bundleDescription;
    private int totalLessonNumber;
    private List<CourseDtoForBundleId> courses;

}
