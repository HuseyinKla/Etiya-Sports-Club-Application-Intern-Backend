package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
