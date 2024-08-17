package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.requests.InitialProgressDto;
import com.example.EtiyaSportsClub.dtos.requests.ProgressBundleDto;
import com.example.EtiyaSportsClub.dtos.responses.ProgressGetDto;
import com.example.EtiyaSportsClub.dtos.responses.ProgressForCalendar;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IProgressGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import com.example.EtiyaSportsClub.repos.IProgressRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgressService {

    private final IProgressRepository progressRepository;
    private final IUserRepository userRepository;
    private final IBundleRepository bundleRepository;



    public ProgressService(IProgressRepository progressRepository, IUserRepository userRepository, IBundleRepository bundleRepository){
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.bundleRepository = bundleRepository;
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

    public ProgressForCalendar getProgressByUsername(String username) {
        Optional<UserEntity> foundedUser = userRepository.findByUserName(username);
        if (foundedUser.isPresent()){
            ProgressEntity foundedProgress = progressRepository.findByUser_UserId(foundedUser.get().getUserId())
                    .orElseThrow(() -> new RuntimeException("Progress not found"));
            return IProgressGetMapper.INSTANCE.progressToProgressForCalendar(foundedProgress);
        }else{
            throw new RuntimeException("User Not Found");
        }

    }

    public ProgressEntity initProgress(InitialProgressDto newProgress) {
        Optional<UserEntity> foundedUser = userRepository.findByUserName(newProgress.getUsername());
        if (foundedUser.isPresent()) {
            ProgressEntity progressEntity = new ProgressEntity();
            progressEntity.setUser(foundedUser.get());
            BundleEntity bundle = bundleRepository.findById(newProgress.getBundleId())
                    .orElseThrow(() -> new RuntimeException("Bundle not found"));
            progressEntity.setBundle(bundle);

            progressEntity.setRemainingCourseNumber(newProgress.getRemainingCourseNumber());
            progressEntity.setProcessStatus(ProgressEntity.processStatus.PROCESSING);

            try {
                return progressRepository.save(progressEntity);
            }catch (Exception e){
                return (ProgressEntity) ResponseEntity.badRequest();
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public ProgressForCalendar updateProgressBundle(ProgressBundleDto progressBundleDto) {
        UserEntity foundedUser = userRepository.findByUserName(progressBundleDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ProgressEntity foundedProgress = progressRepository.findByUser_UserIdAndProcessStatus(foundedUser.getUserId(), ProgressEntity.processStatus.PROCESSING)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        if (foundedProgress.getRemainingCourseNumber() == 1){
            foundedProgress.setRemainingCourseNumber(0);
            foundedProgress.setProcessStatus(ProgressEntity.processStatus.FINISHED);
        }else{
            foundedProgress.setRemainingCourseNumber(foundedProgress.getRemainingCourseNumber() - 1);
        }
        progressRepository.save(foundedProgress);

        return IProgressGetMapper.INSTANCE.progressToProgressForCalendar(foundedProgress);
    }

    public List<ProgressGetDto> getAllProgressByActivity() {
        List<ProgressEntity> allProgresses = progressRepository.findAll().stream()
                .filter(progress -> progress.getRemainingCourseNumber() != 0)
                .collect(Collectors.toList());

        return IProgressGetMapper.INSTANCE.progressesToGetAllProgressesDto(allProgresses);
    }
}