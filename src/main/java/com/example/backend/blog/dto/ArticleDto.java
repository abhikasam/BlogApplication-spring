package com.example.backend.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDto {
    private int articleId;
    private String title;
    private String content;
    private String url;
    private LocalDateTime publishedOn;
    private AuthorDto author;
    private List<CategoryDto> categories=new ArrayList<>();

    private int[] categoryIds(){
        return categories.stream().mapToInt(i->i.getCategoryId()).toArray();
    }

}
