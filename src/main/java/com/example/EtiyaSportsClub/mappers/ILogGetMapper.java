package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.responses.LogGetDto;
import com.example.EtiyaSportsClub.entities.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ILogGetMapper {

    ILogGetMapper INSTANCE = Mappers.getMapper(ILogGetMapper.class);


    @Mapping(source = "user.userName", target = "username")
    LogGetDto logToGetLogDto(LogEntity log);
    List<LogGetDto> logsToGetAllLogsDto(List<LogEntity> logs);


    LogEntity logGetDtoToLogEntity(LogGetDto logGetDto);

}
