package com.example.backend.repository;

import com.example.backend.entities.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleCategoryRepository extends JpaRepository<ArticleCategory,Integer> {
}
