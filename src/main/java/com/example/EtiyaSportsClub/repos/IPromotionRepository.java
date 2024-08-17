package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.entities.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionRepository extends JpaRepository<PromotionEntity,Long> {

}
