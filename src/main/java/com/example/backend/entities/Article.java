package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleid")
    private int articleId;
    @Column(name = "title")

    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "url")
    private String url;

    @Column(name = "publishedon")
    private LocalDateTime publishedOn;

    @ManyToOne
    @JoinColumn(name = "authorid")
    private Author author;

    @OneToMany(mappedBy = "article")
    private Set<ArticleCategory> articleCategories;

}
