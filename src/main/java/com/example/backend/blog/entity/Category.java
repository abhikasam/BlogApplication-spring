package com.example.backend.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private int categoryId;
    @Column(name = "categoryname")
    private String CategoryName;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ArticleCategory> articleCategories;

    public int[] articleIds(){
        if(articleCategories!=null){
            return articleCategories.stream()
                    .mapToInt(i->i.getArticleId())
                    .toArray();
        }
        return new int[]{};
    }

    @JsonProperty("articles")
    public List<Article> articles(){
        if(articleCategories!=null){
            return articleCategories.stream().map(i->i.getArticle()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
