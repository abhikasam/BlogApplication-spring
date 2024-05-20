package com.example.backend.controllers;

import com.example.backend.entities.Article;
import com.example.backend.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = {"","/index"})
    public List<Article> index(){
        return articleService.articles();
    }

    @GetMapping("{id}")
    public Article getArticle(@PathVariable int id){
        return articleService.getArticle(id);
    }

}
