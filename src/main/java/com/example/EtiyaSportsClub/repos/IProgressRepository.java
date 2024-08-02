package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgressRepository extends JpaRepository<ProgressEntity, Long> {
}
