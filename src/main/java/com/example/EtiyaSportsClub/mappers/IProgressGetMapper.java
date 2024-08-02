package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.ProgressGetDto;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IProgressGetMapper {

    IProgressGetMapper INSTANCE = Mappers.getMapper(IProgressGetMapper.class);

    @Mapping(source = "bundle.bundleName", target = "bundleName")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.userId", target = "userId")
    ProgressGetDto progressToGetProgressDto(ProgressEntity progress);
    List<ProgressGetDto> progressesToGetAllProgressesDto(List<ProgressEntity> progresses);
}
