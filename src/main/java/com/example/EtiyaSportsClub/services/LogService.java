package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.LogGetDto;
import com.example.EtiyaSportsClub.dtos.requests.LogCreateDto;
import com.example.EtiyaSportsClub.entities.LogEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.ILogGetMapper;
import com.example.EtiyaSportsClub.repos.ILogRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    private ILogRepository logRepository;
    @Autowired
    private IUserRepository userRepository;

    public LogService(ILogRepository logRepository, IUserRepository userRepository){
        this.logRepository = logRepository;
        this.userRepository= userRepository;
    }

    public List<LogGetDto> getAllLogsDto() {

        List<LogEntity> logs = logRepository.findAll().stream()
                .filter(log -> Objects.equals(log.getUser().getRole().getRoleName(), "member"))
                .collect(Collectors.toList());

        return ILogGetMapper.INSTANCE.logsToGetAllLogsDto(logs);
    }

    public LogGetDto createNewLog(LogCreateDto logInfo) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserEntity foundedUser = userRepository.findByUserName(logInfo.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not found"));

        LogGetDto newLog = new LogGetDto();
        newLog.setLogDate(timestamp);
        newLog.setAction(logInfo.getAction());
        newLog.setUsername(logInfo.getUsername());

        LogEntity newLogEntity = new LogEntity();
        newLogEntity.setLogDate(timestamp);
        newLogEntity.setAction(logInfo.getAction());
        newLogEntity.setUser(foundedUser);

        logRepository.save(newLogEntity);
        return newLog;
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

