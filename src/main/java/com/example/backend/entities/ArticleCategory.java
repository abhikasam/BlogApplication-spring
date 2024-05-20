package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ArticleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleCategoryId;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
}
