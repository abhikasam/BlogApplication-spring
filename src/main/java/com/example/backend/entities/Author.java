package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
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

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Article> articles;

}
