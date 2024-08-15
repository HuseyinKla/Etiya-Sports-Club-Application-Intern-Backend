package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.BundleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBundleRepository extends JpaRepository<BundleEntity, Long> {

    List<BundleEntity> findByUser_UserId(Long userId);



}
