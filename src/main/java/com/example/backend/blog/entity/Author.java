package com.example.backend.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorid")
    private int authorId;
    @Column(name = "authorname")
    private String authorName;

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private List<Article> articles;
}
