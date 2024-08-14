package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.dtos.UserGetDto;
import com.example.EtiyaSportsClub.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserNameAndPassword(String username, String password);
    Optional<UserEntity> findByUserName(String username);


    @Query("SELECT u FROM UserEntity u LEFT JOIN PurchaseEntity p ON u.userId = p.user.userId WHERE p.purchaseId IS NULL")
    List<UserEntity> findUsersWithoutPurchases();
}
