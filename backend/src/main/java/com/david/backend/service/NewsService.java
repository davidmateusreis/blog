package com.david.backend.service;

import com.david.backend.entity.News;
import com.david.backend.repository.NewsRepository;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import org.jdom2.Element;
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

                List<Element> contentElements = entry.getForeignMarkup();
                for (Element element : contentElements) {
                    if ("content".equals(element.getName())
                            && "http://search.yahoo.com/mrss/".equals(element.getNamespaceURI())) {
                        if (element.getAttributes().size() > 0) {
                            news.setImageUrl(element.getAttributeValue("url"));
                            break;
                        } else {
                            System.out.println("Found media:content element without attributes for entry with title: "
                                    + entry.getTitle());
                        }
                    }
                }

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