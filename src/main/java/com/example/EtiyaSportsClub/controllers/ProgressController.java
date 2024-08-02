package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.ProgressGetDto;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import com.example.EtiyaSportsClub.services.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/progresses")
public class ProgressController {

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

    @PostMapping
    public ProgressEntity createProgress(@RequestBody ProgressEntity newProgress){
        return progressService.createProgress(newProgress);
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
