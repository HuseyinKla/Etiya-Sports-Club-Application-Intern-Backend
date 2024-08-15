package com.example.EtiyaSportsClub.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitialProgressDto {

    private String username;
    private Long bundleId;
    private int remainingCourseNumber;

}
