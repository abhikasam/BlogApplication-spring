package com.example.backend.blog.services;

import com.example.backend.blog.dto.ArticleDto;
import com.example.backend.blog.dto.AuthorDto;
import com.example.backend.blog.dto.CategoryDto;
import com.example.backend.blog.entity.Article;
import com.example.backend.blog.entity.Author;
import com.example.backend.blog.repository.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional("secondaryTransactionManager")
public class ArticleService {
    @Autowired
    private IArticleRepository articleRepository;

    public List<ArticleDto> articles(){
        var articles=articleRepository.findAll();
        return getArticleDtos(articles);
    }

    public List<ArticleDto> getArticleDtos(List<Article> articles){
        List<ArticleDto> articleDtos=new ArrayList<>();
        for(var article : articles){
            var articleDto=new ArticleDto();
            articleDto.setArticleId(article.getArticleId());;
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
            articleDto.setUrl(article.getUrl());
            articleDto.setPublishedOn(article.getPublishedOn());
            var authordto=new AuthorDto();
            authordto.setAuthorId(article.getAuthor().getAuthorId());
            authordto.setAuthorName(article.getAuthor().getAuthorName());
            articleDto.setAuthor(authordto);

            List<CategoryDto> categories=new ArrayList<>();
            for(var articleCategory : article.getArticleCategories()){
                var categoryDto=new CategoryDto();
                categoryDto.setCategoryId(articleCategory.getCategoryId());
                categoryDto.setCategoryName(articleCategory.getCategory().getCategoryName());
                categories.add(categoryDto);
            }
            articleDto.setCategories(categories);
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }

    public Article getArticle(int id){
        Optional<Article> articleOptional=articleRepository.findById(id);
        if(articleOptional.isPresent())
            return articleOptional.get();
        return new Article();
    }

    public List<ArticleDto> getArticles(int[] authorIds,int[] categoryIds){
        var artlces= articleRepository.findArticlesByAuthorIdsAndCategoryIds(authorIds,categoryIds);
        return getArticleDtos(artlces);
    }

}
