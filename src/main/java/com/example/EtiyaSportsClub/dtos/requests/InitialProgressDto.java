package com.example.EtiyaSportsClub.dtos.requests;


import com.example.EtiyaSportsClub.entities.ProgressEntity;
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
    private ProgressEntity.processStatus processStatus;

}
