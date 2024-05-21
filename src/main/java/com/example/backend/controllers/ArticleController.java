package com.example.backend.controllers;

import com.example.backend.blog.dto.ArticleDto;
import com.example.backend.blog.entity.Article;
import com.example.backend.blog.services.AuthorService;
import com.example.backend.blog.services.CategoryService;
import com.example.backend.shared.entity.XPagination;
import com.example.backend.blog.services.ArticleService;
import com.example.backend.shared.service.XPaginationService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private XPaginationService xPaginationService;
    @GetMapping(value = {"","/index"})
    public List<ArticleDto> index(@RequestHeader("x-pagination") String xpag, HttpServletResponse response){
        XPagination xPagination=xPaginationService.getxPagination(xpag);
        var articles=articleService.articles();
        articles=xPaginationService.getPaginatedResult(articles,xPagination);
        xPaginationService.setXPagination(xPagination,response);
        return articles;
    }

    @GetMapping("{authors}/{categories}")
    public List<ArticleDto> getArticle(@PathVariable String authors,
                                    @PathVariable String categories,
                                    @RequestHeader("x-pagination") String xpag,
                                    HttpServletResponse response){

        XPagination xPagination=xPaginationService.getxPagination(xpag);

        Gson gson=new Gson();
        if(authors.length()>1)
            authors=authors.substring(1);
        else
            authors="[]";

        if(categories.length()>1)
            categories=categories.substring(1);
        else
            categories="[]";

        int[] authorIds= gson.fromJson(authors,int[].class);
        int[] categoryIds=gson.fromJson(categories,int[].class);

        if(authorIds.length==0){
            authorIds=authorService.authors().stream().mapToInt(i->i.getAuthorId()).toArray();
        }
        if(categoryIds.length==0){
            categoryIds=categoryService.categories().stream().mapToInt(i->i.getCategoryId()).toArray();
        }

        var articles=articleService.getArticles(authorIds,categoryIds);

        articles=xPaginationService.getPaginatedResult(articles,xPagination);

        xPaginationService.setXPagination(xPagination,response);
        return articles;
    }

}
