package com.example.EtiyaSportsClub.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "RoleTable", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"roleName"})
})
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private int userRoleNumber;
    private String roleName;


    @Enumerated(EnumType.ORDINAL)
    private activityStatus activityStatus;

    public enum activityStatus {
        DEACTIVE,
        ACTIVE
    }

}
