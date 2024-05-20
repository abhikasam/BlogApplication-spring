package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Article article;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonManagedReference
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
