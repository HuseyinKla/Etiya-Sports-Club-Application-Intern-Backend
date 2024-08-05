package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findByBundle_BundleId(Long bundleId);
}
