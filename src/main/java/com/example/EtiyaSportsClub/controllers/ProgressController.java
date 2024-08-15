package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.responses.ProgressGetDto;
import com.example.EtiyaSportsClub.dtos.requests.InitialProgressDto;
import com.example.EtiyaSportsClub.dtos.requests.ProgressBundleDto;
import com.example.EtiyaSportsClub.dtos.responses.ProgressForCalendar;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import com.example.EtiyaSportsClub.services.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresses")
public class ProgressController {

    private final ProgressService progressService;

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

    @GetMapping("/active")
    public List<ProgressGetDto> getAllProgressByActivity(){
        return progressService.getAllProgressByActivity();
    }

    @PostMapping("/initProgress")
    public ProgressEntity initProgress(@RequestBody InitialProgressDto newProgress){
        return progressService.initProgress(newProgress);
    }


    @PutMapping("/{progressId}")
    public ProgressEntity updateProgress(@PathVariable Long progressId, @RequestBody ProgressEntity newProgress){
        return progressService.updateProgress(progressId, newProgress);
    }

    @PutMapping("/updateProgressBundle")
    public ProgressForCalendar updateProgressBundle(@RequestBody ProgressBundleDto progressBundleDto){
        return progressService.updateProgressBundle(progressBundleDto);
    }



    @DeleteMapping("/{progressId}")
    public void deleteProgress(@PathVariable Long progressId){
        progressService.deleteProgress(progressId);
    }




}
