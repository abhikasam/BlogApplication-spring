package com.example.backend.blog.repository;

import com.example.backend.blog.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleCategoryRepository extends JpaRepository<ArticleCategory,Integer> {
}
