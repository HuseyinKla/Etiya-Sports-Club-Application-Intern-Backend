package com.example.EtiyaSportsClub.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogGetDto {

    private Long logId;
    private String username;
    private Timestamp logDate;
    private String action;


}
