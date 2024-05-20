package com.example.backend.services;

import com.example.backend.entities.Article;
import com.example.backend.entities.Author;
import com.example.backend.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private IArticleRepository articleRepository;

    public List<Article> articles(){
        return articleRepository.findAll();
    }

    public Article getArticle(int id){
        Optional<Article> articleOptional=articleRepository.findById(id);
        if(articleOptional.isPresent())
            return articleOptional.get();
        return new Article();
    }

}
