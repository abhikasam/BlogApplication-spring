package com.example.backend.blog.services;

import com.example.backend.blog.entity.Author;
import com.example.backend.blog.repository.IAuthorRepository;
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

    public Author getAuthor(int id){
        var author=authorRepository.findById(id);
        if(author.isPresent()){
            return author.get();
        }
        return new Author();
    }

}
