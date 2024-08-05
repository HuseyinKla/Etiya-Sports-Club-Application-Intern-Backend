package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.BundleGetDto;
import com.example.EtiyaSportsClub.dtos.CourseDtoForBundleId;
import com.example.EtiyaSportsClub.dtos.CourseGetDto;
import com.example.EtiyaSportsClub.dtos.CoursesByBundleIdDto;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import com.example.EtiyaSportsClub.mappers.ICourseGetMapper;
import com.example.EtiyaSportsClub.repos.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.EtiyaSportsClub.services.BundleService;


@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private BundleService bundleService;


    public CourseService(ICourseRepository courseRepository, BundleService bundleService){
        this.courseRepository = courseRepository;
        this.bundleService = bundleService;
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

    public CoursesByBundleIdDto getCoursesByBundleId(Long bundleId) {
        List<CourseEntity> courseEntities = courseRepository.findByBundle_BundleId(bundleId);
        List<CourseDtoForBundleId> courseDtos = courseEntities.stream()
                .map(course -> {
                    CourseDtoForBundleId courseDto = new CourseDtoForBundleId();
                    courseDto.setCourseId(course.getCourseId());
                    courseDto.setCourseName(course.getCourseName());
                    courseDto.setCourseDescription(course.getCourseDescription());
                    return courseDto;
                })
                .collect(Collectors.toList());

        CoursesByBundleIdDto response = new CoursesByBundleIdDto();
        response.setBundleId(bundleId);


        BundleGetDto foundedBundle = bundleService.getOneBundleDto(bundleId);
        response.setBundleName(foundedBundle.getBundleName());
        response.setBundleDescription(foundedBundle.getBundleDescription());
        response.setTotalLessonNumber(foundedBundle.getTotalLessonNumber());
        response.setCourses(courseDtos);
        return response;
    }
}
