package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
}
