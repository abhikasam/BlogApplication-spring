package com.example.backend.services;

import com.example.backend.entities.ArticleCategory;
import com.example.backend.repository.IArticleCategoryRepository;
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
