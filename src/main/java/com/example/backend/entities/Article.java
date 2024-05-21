package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JsonIgnore
    private List<ArticleCategory> articleCategories;

    @JsonProperty("author")
    public Author getAuthor(){
        var author=new Author();
        if(this.author!=null){
            author.setAuthorId(this.author.getAuthorId());
            author.setAuthorName(this.author.getAuthorName());
        }
        return author;
    }
    public int[] categoryIds(){
        if(articleCategories!=null){
            return articleCategories.stream().mapToInt(i->i.getCategoryId()).toArray();
        }
        return new int[]{};
    }

    @JsonProperty("categories")
    public List<Category> categories(){
        var categories=new ArrayList<Category>();
        if(articleCategories!=null){
            for(var articleCategory : articleCategories){
                var category=new Category();
                category.setCategoryId(articleCategory.getCategoryId());
                category.setCategoryName(articleCategory.getCategory().getCategoryName());
                categories.add(category);
            }
        }
        return categories;
    }

}
