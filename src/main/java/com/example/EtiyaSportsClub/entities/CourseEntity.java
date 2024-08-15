package com.example.EtiyaSportsClub.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CourseTable")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "bundleId")
    private BundleEntity bundle;

    private String courseName;
    private String courseDescription;
}

