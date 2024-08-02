package com.example.EtiyaSportsClub.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ProgressTable")
@Data
public class ProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bundleId")
    private BundleEntity bundle;


    private int remainingCourseNumber;
}
