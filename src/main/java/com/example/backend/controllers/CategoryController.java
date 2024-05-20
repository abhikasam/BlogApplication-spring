package com.example.backend.controllers;

import com.example.backend.entities.Category;
import com.example.backend.entities.XPagination;
import com.example.backend.services.CategoryService;
import com.example.backend.services.XPaginationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return categoryService.categories();
    }

    @GetMapping("{id}")
    public Category getCategory(
            @PathVariable int id,
            @RequestHeader("x-pagination") String xpag,
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
