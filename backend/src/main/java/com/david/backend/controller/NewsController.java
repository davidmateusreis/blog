package com.david.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.backend.entity.News;
import com.david.backend.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @CrossOrigin(origins = { "http://localhost:4200" })
    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/update")
    public String updateNewsFromRSS() {
        newsService.fetchAndSaveNewsFromRSS("https://www.nintendolife.com/feeds/news");
        return "News updated successfully!";
    }
}
