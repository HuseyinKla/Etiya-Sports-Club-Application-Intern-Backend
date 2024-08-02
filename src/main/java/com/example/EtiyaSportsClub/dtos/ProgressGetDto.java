package com.example.EtiyaSportsClub.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressGetDto {

    private Long progressId;
    private Long userId;
    private String name;
    private String bundleName;
    private int remainingCourseNumber;

}