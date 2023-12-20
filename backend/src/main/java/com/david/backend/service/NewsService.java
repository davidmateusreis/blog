package com.david.backend.service;

import com.david.backend.entity.News;
import com.david.backend.repository.NewsRepository;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public void fetchAndSaveNewsFromRSS(String rssFeedUrl) {
        try {
            FeedFetcher feedFetcher = new HttpURLFeedFetcher();

            URL feedUrl = new URL(rssFeedUrl);
            SyndFeed syndFeed = feedFetcher.retrieveFeed(feedUrl);

            List<News> newsList = new ArrayList<>();

            for (SyndEntry entry : syndFeed.getEntries()) {
                News news = new News();
                news.setTitle(entry.getTitle());
                news.setLink(entry.getLink());
                news.setDescription(entry.getDescription().getValue());
                newsList.add(news);
            }

            newsRepository.saveAll(newsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}