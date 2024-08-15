package com.example.EtiyaSportsClub.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "LogTable")
@Data
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;


    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;


    private Timestamp logDate;
    private String action;
}
