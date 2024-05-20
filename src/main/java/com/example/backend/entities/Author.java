package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorid")
    private int authorId;
    @Column(name = "authorname")
    private String authorName;

    @OneToMany(mappedBy = "author")
    private Set<Article> articles;

}
