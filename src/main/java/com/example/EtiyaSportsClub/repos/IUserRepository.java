package com.example.EtiyaSportsClub.repos;

import com.example.EtiyaSportsClub.dtos.UserGetDto;
import com.example.EtiyaSportsClub.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserNameAndPassword(String username, String password);
}
