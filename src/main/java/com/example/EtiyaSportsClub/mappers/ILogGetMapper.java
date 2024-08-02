package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.LogGetDto;
import com.example.EtiyaSportsClub.entities.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ILogGetMapper {

    ILogGetMapper INSTANCE = Mappers.getMapper(ILogGetMapper.class);


    @Mapping(source = "user.userId", target = "userId")
    LogGetDto logToGetLogDto(LogEntity log);
    List<LogGetDto> logsToGetAllLogsDto(List<LogEntity> logs);

}
