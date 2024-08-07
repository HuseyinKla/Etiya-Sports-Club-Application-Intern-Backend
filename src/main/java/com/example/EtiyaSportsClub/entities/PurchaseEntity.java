package com.example.EtiyaSportsClub.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "PurchaseTable")
@Data
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;


    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bundleId")
    private BundleEntity bundle;

    private Timestamp purchaseDate;
    private int totalLessonNumber;

}

