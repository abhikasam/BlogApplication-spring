package com.example.backend.controllers;

import com.example.backend.entities.Article;
import com.example.backend.entities.XPagination;
import com.example.backend.services.ArticleService;
import com.example.backend.services.XPaginationService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private XPaginationService xPaginationService;
    @GetMapping(value = {"","/index"})
    public List<Article> index(@RequestHeader("x-pagination") String xpag, HttpServletResponse response){
        XPagination xPagination=xPaginationService.getxPagination(xpag);
        var articles=articleService.articles();
        articles=xPaginationService.getPaginatedResult(articles,xPagination);
        xPaginationService.setXPagination(xPagination,response);
        return articles;
    }

    @GetMapping("{authors}/{categories}")
    public List<Article> getArticle(@PathVariable String authors,
                                    @PathVariable String categories,
                                    @RequestHeader("x-pagination") String xpag,
                                    HttpServletResponse response){

        XPagination xPagination=xPaginationService.getxPagination(xpag);

        Gson gson=new Gson();
        int[] authorIds= gson.fromJson(authors,int[].class);
        int[] categoryIds=gson.fromJson(categories,int[].class);

        var articles=articleService.articles().stream()
                .filter(i-> Arrays.stream(authorIds).anyMatch(a-> a==i.getAuthorId()))
                .filter(i-> Arrays.stream(i.categoryIds())
                        .anyMatch(ic-> Arrays.stream(categoryIds).anyMatch(c-> ic==c  ) ))
                .collect(Collectors.toList());
        xPaginationService.setXPagination(xPagination,response);
        return articles;
    }

}
