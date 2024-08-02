package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.ProgressGetDto;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import com.example.EtiyaSportsClub.mappers.IProgressGetMapper;
import com.example.EtiyaSportsClub.repos.IProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    IProgressRepository progressRepository;

    public ProgressService(IProgressRepository progressRepository){
        this.progressRepository = progressRepository;
    }



    public List<ProgressGetDto> getAllProgressDto() {
        List<ProgressEntity> progresses = progressRepository.findAll();
        return IProgressGetMapper.INSTANCE.progressesToGetAllProgressesDto(progresses);
    }


    public ProgressGetDto getOneProgressDto(Long progressId) {
        Optional<ProgressEntity> optionalProgress = progressRepository.findById(progressId);
        if(optionalProgress.isPresent()){
            return IProgressGetMapper.INSTANCE.progressToGetProgressDto(optionalProgress.get());
        }else{
            throw new RuntimeException("Progress not found");
        }
    }

    public ProgressEntity createProgress(ProgressEntity newProgress) {
        return progressRepository.save(newProgress);
    }

    public ProgressEntity updateProgress(Long progressId, ProgressEntity newProgress) {
        Optional<ProgressEntity> progress = progressRepository.findById(progressId);
        if(progress.isPresent()){
            ProgressEntity foundedProgress = progress.get();
            foundedProgress.setRemainingCourseNumber(foundedProgress.getRemainingCourseNumber());
            progressRepository.save(foundedProgress);
            return foundedProgress;
        }
        return null;
    }


    public void deleteProgress(Long progressId) {
        progressRepository.deleteById(progressId);
    }
}