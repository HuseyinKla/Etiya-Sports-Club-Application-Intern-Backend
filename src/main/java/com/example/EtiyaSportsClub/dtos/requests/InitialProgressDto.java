package com.example.EtiyaSportsClub.dtos.requests;


import com.example.EtiyaSportsClub.entities.ProgressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitialProgressDto {

    private Long userId;
    private Long bundleId;
    private int totalLessonNumber;
    private ProgressEntity.processStatus processStatus;

}
