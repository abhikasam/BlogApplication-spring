package com.example.backend.blog.services;

import com.example.backend.blog.entity.Category;
import com.example.backend.blog.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> categories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(int id){
        var category=categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return new Category();
    }

}
