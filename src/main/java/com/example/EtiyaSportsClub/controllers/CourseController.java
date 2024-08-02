package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.CourseGetDto;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import com.example.EtiyaSportsClub.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/courses")
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseGetDto> getAllCoursesDto(){
        return courseService.getAllCoursesDto();
    }

    @GetMapping("/{courseId}")
    public CourseGetDto getOneCourseDto(@PathVariable Long courseId){
        return courseService.getOneCourseDto(courseId);
    }

    @PostMapping
    public CourseEntity createCourse(@RequestBody CourseEntity newCourse){
        return courseService.creteCourse(newCourse);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
    }



}