package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.LogGetDto;
import com.example.EtiyaSportsClub.entities.LogEntity;
import com.example.EtiyaSportsClub.mappers.ILogGetMapper;
import com.example.EtiyaSportsClub.repos.ILogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    @Autowired
    private ILogRepository logRepository;

    public LogService(ILogRepository logRepository){
        this.logRepository = logRepository;
    }

    public List<LogGetDto> getAllLogsDto() {

        List<LogEntity> logs = logRepository.findAll();

        return ILogGetMapper.INSTANCE.logsToGetAllLogsDto(logs);
    }

    public LogEntity createNewLog(LogEntity newLog) {
        return logRepository.save(newLog);
    }

    public LogGetDto findOneLogDto(Long logId) {
        Optional<LogEntity> logOptional = logRepository.findById(logId);
        if (logOptional.isPresent()){
            return ILogGetMapper.INSTANCE.logToGetLogDto(logOptional.get());
        }else{
            throw new RuntimeException("Log not found");
        }
    }

}

