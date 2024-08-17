package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.responses.ProgressGetDto;
import com.example.EtiyaSportsClub.dtos.requests.ProgressDto;
import com.example.EtiyaSportsClub.dtos.responses.ProgressForCalendar;
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

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "bundleId", target = "bundle.bundleId")
    @Mapping(source = "remainingCourseNumber", target = "remainingCourseNumber")
    ProgressEntity initialProgressDtoToProgressEntity(ProgressDto progressDto);


    @Mapping(source = "user.userName", target = "username")
    @Mapping(source = "bundle.bundleName", target = "bundleName")
    @Mapping(source = "bundle.bundleDescription", target = "bundleDescription")
    @Mapping(source = "bundle.totalLessonNumber", target = "totalLessonNumber")
    @Mapping(source = "remainingCourseNumber", target = "remainingCourseNumber")
    ProgressForCalendar progressToProgressForCalendar(ProgressEntity progress);

}
