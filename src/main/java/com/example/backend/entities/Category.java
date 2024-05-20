package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

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
    @JsonBackReference
    private List<ArticleCategory> articleCategories;

    public int[] articleIds(){
        if(articleCategories!=null){
            return articleCategories.stream()
                    .mapToInt(i->i.getArticleId())
                    .toArray();
        }
        return new int[]{};
    }

}
