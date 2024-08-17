package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.responses.CourseGetDto;
import com.example.EtiyaSportsClub.dtos.responses.CoursesByBundleIdDto;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ICourseGetMapper {

    ICourseGetMapper INSTANCE = Mappers.getMapper(ICourseGetMapper.class);

    @Mapping(source = "bundle.bundleName", target = "bundleName")
    @Mapping(source = "bundle.bundleId", target = "bundleId")
    CourseGetDto courseToGetCourseDto(CourseEntity course);
    List<CourseGetDto> coursestoGetAllCoursesDto(List<CourseEntity> courses);
    List<CoursesByBundleIdDto> getAllCoursesByBundleId(List<CourseEntity> courses);


}
