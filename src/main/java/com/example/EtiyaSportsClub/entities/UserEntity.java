package com.example.EtiyaSportsClub.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserTable", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"eMail"}),
        @UniqueConstraint(columnNames = {"userName"})
})
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", nullable = false)
    private RoleEntity role;


    private String userName;
    private String name;
    private String eMail;
    private String password;
    private Timestamp createdAt;


}