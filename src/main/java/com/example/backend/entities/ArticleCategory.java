package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articlecategory")
public class ArticleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articlecategoryid")
    private int articleCategoryId;

    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    public int getArticleId(){
        if(article!=null)
            return article.getArticleId();
        return 0;
    }

    public int getCategoryId(){
        if(category!=null)
            return category.getCategoryId();
        return 0;
    }

}
