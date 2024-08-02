package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.LogGetDto;
import com.example.EtiyaSportsClub.entities.LogEntity;
import com.example.EtiyaSportsClub.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/logs")
public class LogController {

    @Autowired
    private LogService logService;

    public LogController(LogService logService){
        this.logService = logService;
    }

    @GetMapping
    public List<LogGetDto> getAllLogsDto(){
        return logService.getAllLogsDto();
    }

    @GetMapping("/{logId}")
    public LogGetDto findOneLogDto(@PathVariable Long logId){
        return logService.findOneLogDto(logId);
    }

    @PostMapping
    public LogEntity createNewLog(@RequestBody LogEntity newLog){
        return logService.createNewLog(newLog);

    }

}
