package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogRepository extends JpaRepository<LogEntity, Long> {
}
