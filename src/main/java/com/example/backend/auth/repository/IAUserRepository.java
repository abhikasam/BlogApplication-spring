package com.example.backend.auth.repository;

import com.example.backend.auth.entity.AUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAUserRepository extends JpaRepository<AUser,Integer> {
}
