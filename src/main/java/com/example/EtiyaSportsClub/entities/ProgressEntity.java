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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bundleId")
    private BundleEntity bundle;


    private int remainingCourseNumber;

    @Enumerated(EnumType.ORDINAL)
    private ProgressEntity.processStatus processStatus;

    public enum processStatus {
        NOT,
        PROCESSING,
        FINISHED
    }
}
