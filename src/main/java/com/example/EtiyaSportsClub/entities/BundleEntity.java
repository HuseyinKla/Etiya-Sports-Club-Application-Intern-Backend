package com.example.EtiyaSportsClub.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BundleTable")
@Data
public class BundleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bundleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;


    private String bundleName;
    private String bundleDescription;
    private double bundlePrice;
    private int totalLessonNumber;

    @OneToMany(mappedBy = "bundle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courses;


}
