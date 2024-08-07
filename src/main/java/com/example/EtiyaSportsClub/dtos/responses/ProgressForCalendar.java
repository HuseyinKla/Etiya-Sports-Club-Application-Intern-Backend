package com.example.EtiyaSportsClub.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressForCalendar {
    String username;
    String bundleName;
    String bundleDescription;
    int totalLessonNumber;
    int remainingCourseNumber;
}
