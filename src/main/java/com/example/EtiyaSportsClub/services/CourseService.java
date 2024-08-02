package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.CourseGetDto;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import com.example.EtiyaSportsClub.mappers.ICourseGetMapper;
import com.example.EtiyaSportsClub.repos.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    public List<CourseGetDto> getAllCoursesDto() {
        List<CourseEntity> courses = courseRepository.findAll();
        return ICourseGetMapper.INSTANCE.coursestoGetAllCoursesDto(courses);
    }

    public CourseGetDto getOneCourseDto(Long courseId) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()){
            return ICourseGetMapper.INSTANCE.courseToGetCourseDto(optionalCourse.get());
        }else{
            throw new RuntimeException("Course not found");
        }

    }

    public CourseEntity creteCourse(CourseEntity newCourse) {
        return courseRepository.save(newCourse);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById((courseId));
    }
}
