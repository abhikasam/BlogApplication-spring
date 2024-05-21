package com.example.backend.blog.services;

import com.example.backend.blog.entity.ArticleCategory;
import com.example.backend.blog.repository.IArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCateoryService {
    @Autowired
    private IArticleCategoryRepository articleCategoryRepository;
    public List<ArticleCategory> articleCategories(){
        return articleCategoryRepository.findAll();
    }
}
