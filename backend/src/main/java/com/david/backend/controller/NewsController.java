package com.david.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.backend.dto.NewsPageDto;
import com.david.backend.entity.News;
import com.david.backend.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @GetMapping
    public ResponseEntity<NewsPageDto> getAllNews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String searchQuery) {
        NewsPageDto newsPageDto = newsService.getAllNews(page, size, searchQuery);
        return new ResponseEntity<>(newsPageDto, HttpStatus.OK);
    }

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @GetMapping("{slug}")
    public ResponseEntity<News> getNewsDetailsBySlug(@PathVariable String slug) {
        News news = newsService.getNewsDetailsBySlug(slug);
        return news != null
                ? new ResponseEntity<>(news, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
