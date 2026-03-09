package com.botmaker.assignment.repository;

import com.botmaker.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
        Optional<UserEntity> findByEmail(String email);
        boolean existsByEmail(String email);
}
