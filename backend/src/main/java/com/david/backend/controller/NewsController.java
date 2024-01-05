package com.david.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.backend.dtos.NewsPageDto;
import com.david.backend.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @CrossOrigin(origins = { "http://localhost:4200" })
    @GetMapping
    public NewsPageDto getAllNews(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false) String searchQuery) {
        return newsService.getAllNews(page, size, searchQuery);
    }
}
