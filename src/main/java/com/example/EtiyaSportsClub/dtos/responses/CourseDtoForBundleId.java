package com.example.EtiyaSportsClub.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDtoForBundleId {

    private Long courseId;
    private String courseName;
    private String courseDescription;
}
