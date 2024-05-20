package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ArticleCategory> articleCategories;

    public int getAuthorId(){
        if(author!=null)
            return author.getAuthorId();
        return 0;
    }

    public int[] categoryIds(){
        if(articleCategories!=null){
            return articleCategories.stream().mapToInt(i->i.getCategoryId()).toArray();
        }
        return new int[]{};
    }
}
