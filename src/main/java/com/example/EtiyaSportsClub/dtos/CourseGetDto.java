package com.example.EtiyaSportsClub.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseGetDto {

    private Long courseId;
    private Long bundleId;
    private String bundleName;
    private String courseName;
    private String courseDescription;

}
