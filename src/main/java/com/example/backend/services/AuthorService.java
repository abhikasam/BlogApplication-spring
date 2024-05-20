package com.example.backend.services;

import com.example.backend.entities.Author;
import com.example.backend.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private IAuthorRepository authorRepository;

    public List<Author> authors(){
        return authorRepository.findAll();
    }

}
