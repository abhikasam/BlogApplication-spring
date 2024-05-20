package com.example.backend.services;

import com.example.backend.entities.Category;
import com.example.backend.repository.ICategoryRepository;
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

}
