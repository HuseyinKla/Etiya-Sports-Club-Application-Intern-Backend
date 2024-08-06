package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    List<PurchaseEntity> findByUser_UserId(Long userId);

}
