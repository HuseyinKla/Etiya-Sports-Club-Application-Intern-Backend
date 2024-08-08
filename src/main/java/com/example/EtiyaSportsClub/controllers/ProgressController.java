package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.ProgressGetDto;
import com.example.EtiyaSportsClub.dtos.requests.InitialProgressDto;
import com.example.EtiyaSportsClub.dtos.responses.ProgressForCalendar;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import com.example.EtiyaSportsClub.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresses")
public class ProgressController {

    @Autowired
    ProgressService progressService;

    public ProgressController(ProgressService progressService){
        this.progressService = progressService;
    }

    @GetMapping
    public List<ProgressGetDto> getAllProgressDto(){
        return progressService.getAllProgressDto();
    }

    @GetMapping("/{progressId}")
    public ProgressGetDto getOneProgressDto(@PathVariable Long progressId){
        return progressService.getOneProgressDto(progressId);
    }

    /*@GetMapping("/{username}")
    public ProgressForCalendar getProgressByUsername(@PathVariable String username){
        return progressService.getProgressByUsername(username);
    }*/

    @PostMapping
    public ProgressEntity createProgress(@RequestBody InitialProgressDto newProgress){
        return progressService.createProgress(newProgress);
    }

    @PostMapping("/initProgress")
    public ProgressEntity initProgress(@RequestBody InitialProgressDto newProgress){
        return progressService.initProgress(newProgress);
    }


    @PutMapping("/{progressId}")
    public ProgressEntity updateProgress(@PathVariable Long progressId, @RequestBody ProgressEntity newProgress){
        return progressService.updateProgress(progressId, newProgress);
    }

    @DeleteMapping("/{progressId}")
    public void deleteProgress(@PathVariable Long progressId){
        progressService.deleteProgress(progressId);
    }




}
