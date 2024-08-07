package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProgressRepository extends JpaRepository<ProgressEntity, Long> {
    Optional<ProgressEntity> findByUser_UserId(Long userId);


}
