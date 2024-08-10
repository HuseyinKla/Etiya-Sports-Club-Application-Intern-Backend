package com.example.EtiyaSportsClub.dtos.responses;


import com.example.EtiyaSportsClub.entities.BundleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateDto {

    private String courseName;
    private String courseDescription;

}
