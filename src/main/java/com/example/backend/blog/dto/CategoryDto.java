package com.example.backend.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private int categoryId;
    private String categoryName;
    private List<ArticleDto> articles=new ArrayList<>();
}
