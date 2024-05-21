package com.example.backend.blog.repository;

import com.example.backend.blog.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Integer> {
}
