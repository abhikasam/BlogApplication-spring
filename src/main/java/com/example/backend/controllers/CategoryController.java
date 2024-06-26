package com.example.backend.controllers;

import com.example.backend.blog.entity.Category;
import com.example.backend.shared.entity.XPagination;
import com.example.backend.blog.services.CategoryService;
import com.example.backend.shared.service.XPaginationService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private XPaginationService xPaginationService;

    @GetMapping(value = {"","/index"})
    public List<Category> index(){
        List<Category> categories=new ArrayList<>();
        for(var dbCategory : categoryService.categories()){
            var category=new Category();
            category.setCategoryId(dbCategory.getCategoryId());
            category.setCategoryName(dbCategory.getCategoryName());
            categories.add(category);
        }
        return categories;
    }

    @GetMapping("{id}")
    public Category getCategory(
            @PathVariable int id,
            @RequestHeader("x-pagination") @Nullable String xpag,
            HttpServletResponse response
    ){
        XPagination xPagination=xPaginationService.getxPagination(xpag);
        var category=categoryService.getCategory(id);
        category.setArticleCategories(
                xPaginationService.getPaginatedResult(category.getArticleCategories(),xPagination)
        );

        xPaginationService.setXPagination(xPagination,response);
        return category;
    }

}
