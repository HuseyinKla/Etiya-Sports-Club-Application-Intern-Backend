package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.responses.LogGetDto;
import com.example.EtiyaSportsClub.dtos.requests.LogCreateDto;
import com.example.EtiyaSportsClub.services.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
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
    public LogGetDto createNewLog(@RequestBody LogCreateDto newLog){
        return logService.createNewLog(newLog);
    }

}
