package com.example.EtiyaSportsClub.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogGetDto {

    private Long logId;
    private Long userId;
    private Timestamp logDate;
    private String action;


}
