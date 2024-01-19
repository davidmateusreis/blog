package com.david.backend.service;

import com.david.backend.dto.NewsPageDto;
import com.david.backend.entity.News;
import com.david.backend.repository.NewsRepository;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    private static final List<String> RSS_FEED_URLS = List.of(
            "https://www.nintendolife.com/feeds/news",
            "https://www.pushsquare.com/feeds/news",
            "https://www.purexbox.com/feeds/news");

    public void fetchAndSaveNewsFromRSS(String rssFeedUrl) {
        try {
            FeedFetcher feedFetcher = new HttpURLFeedFetcher();
            URL feedUrl = new URL(rssFeedUrl);
            SyndFeed syndFeed = feedFetcher.retrieveFeed(feedUrl);

            List<News> newsList = new ArrayList<>();

            for (SyndEntry entry : syndFeed.getEntries()) {
                String guid = entry.getUri();

                if (newsRepository.findByGuid(guid).isPresent()) {
                    continue;
                }

                String webMaster = syndFeed.getWebMaster();
                String regex = "\\((.*?)\\)";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(webMaster);

                String slug = generateSlug(entry.getTitle());

                News news = new News();
                news.setAuthor(webMaster);
                news.setTitle(entry.getTitle());
                news.setLink(entry.getLink());
                news.setDescription(entry.getDescription().getValue());
                news.setSlug(slug);
                news.setGuid(guid);

                if (matcher.find()) {
                    news.setAuthor(matcher.group(1));
                }

                List<Element> contentElements = entry.getForeignMarkup();
                for (Element element : contentElements) {
                    if ("content".equals(element.getName()) &&
                            "http://search.yahoo.com/mrss/".equals(element.getNamespaceURI())) {
                        if (element.getAttributes().size() > 0) {
                            news.setImageUrl(element.getAttributeValue("url"));
                            break;
                        } else {
                            System.out.println("Found media:content element without attributes for entry with title: "
                                    + entry.getTitle());
                        }
                    }
                }

                Date pubDate = entry.getPublishedDate();
                news.setPubDate(pubDate);

                newsList.add(news);
            }

            if (!newsList.isEmpty()) {
                newsRepository.saveAll(newsList);
                System.out.println(newsList.size() + " news entries saved successfully from RSS: " + rssFeedUrl);
            } else {
                System.out.println("No new news entries to save from RSS: " + rssFeedUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching and saving news from RSS: " + rssFeedUrl);
        }
    }

    private String generateSlug(String input) {
        String normalizedInput = input.trim().toLowerCase().replaceAll("\\s+", "-");
        return normalizedInput.replaceAll("[^a-z0-9-]", "");
    }

    @Scheduled(fixedRate = 1200000)
    public void updateNewsFromRSS() {
        try {
            for (String rssFeedUrl : RSS_FEED_URLS) {
                fetchAndSaveNewsFromRSS(rssFeedUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating news from RSS feeds");
        }
    }

    public NewsPageDto getAllNews(int page, int size, String searchQuery) {
        Page<News> newsPage;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            newsPage = newsRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    searchQuery,
                    searchQuery,
                    PageRequest.of(page, size, Sort.by("pubDate").descending()));
        } else {
            newsPage = newsRepository.findAll(PageRequest.of(page, size, Sort.by("pubDate").descending()));
        }

        return new NewsPageDto(
                newsPage.getContent(),
                newsPage.getTotalElements(),
                newsPage.getTotalPages());
    }

    public News getNewsDetailsBySlug(String slug) {
        return newsRepository.findBySlug(slug)
                .orElseThrow(() -> new NoSuchElementException("News not found for slug: " + slug));
    }
}