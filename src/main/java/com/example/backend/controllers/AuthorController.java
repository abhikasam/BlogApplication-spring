package com.example.backend.controllers;

import com.example.backend.entities.Author;
import com.example.backend.entities.XPagination;
import com.example.backend.services.AuthorService;
import com.example.backend.services.XPaginationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return authorService.authors();
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
