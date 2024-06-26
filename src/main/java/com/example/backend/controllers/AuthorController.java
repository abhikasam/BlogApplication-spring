package com.example.backend.controllers;

import com.example.backend.blog.entity.Author;
import com.example.backend.shared.entity.XPagination;
import com.example.backend.blog.services.AuthorService;
import com.example.backend.shared.service.XPaginationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private XPaginationService xPaginationService;

    @GetMapping(value = {"","/index"})
    public List<Author> index(){
        List<Author> authors=new ArrayList<>();
        for(var dbAuthor : authorService.authors()){
            var author=new Author();
            author.setAuthorId(dbAuthor.getAuthorId());
            author.setAuthorName(dbAuthor.getAuthorName());
            authors.add(author);
        }
        return authors;
    }

    @GetMapping("{id}")
    public Author getAuthor(@PathVariable int id,
                            @RequestHeader("x-pagination") String xpag,
                            HttpServletResponse response){
        XPagination xPagination=xPaginationService.getxPagination(xpag);
        var author=authorService.getAuthor(id);
        author.setArticles(xPaginationService.getPaginatedResult(author.getArticles(),xPagination));
        xPaginationService.setXPagination(xPagination,response);
        return author;
    }

}
