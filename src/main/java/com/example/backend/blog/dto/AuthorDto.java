package com.example.backend.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDto {
    private int authorId;
    private String authorName;
    private List<ArticleDto> articles=new ArrayList<>();
}
