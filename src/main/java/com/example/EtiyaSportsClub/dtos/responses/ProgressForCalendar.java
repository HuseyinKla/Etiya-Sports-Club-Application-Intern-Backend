package com.example.EtiyaSportsClub.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressForCalendar {
    private String username;
    private String bundleName;
    private String bundleDescription;
    private int totalLessonNumber;
    private int remainingCourseNumber;
}
