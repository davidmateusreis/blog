package com.david.backend.service;

import com.david.backend.dtos.NewsPageDto;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                Long extractedNumber = extractNumberFromGuid(guid);

                String webMaster = syndFeed.getWebMaster();
                String regex = "\\((.*?)\\)";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(webMaster);

                String slug = generateSlug(entry.getTitle());

                News news = new News();
                news.setId(extractedNumber);
                news.setAuthor(webMaster);
                news.setTitle(entry.getTitle());
                news.setLink(entry.getLink());
                news.setDescription(entry.getDescription().getValue());
                news.setSlug(slug);

                if (matcher.find()) {
                    news.setAuthor(matcher.group(1));
                }

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

                Date pubDate = entry.getPublishedDate();
                news.setPubDate(pubDate);

                newsList.add(news);
            }

            newsRepository.saveAll(newsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateSlug(String input) {
        String normalizedInput = input.trim().toLowerCase().replaceAll("\\s+", "-");
        return normalizedInput.replaceAll("[^a-z0-9-]", "");
    }

    private Long extractNumberFromGuid(String guid) {
        String[] parts = guid.split("-");
        if (parts.length > 0) {
            try {
                return Long.parseLong(parts[parts.length - 1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Scheduled(fixedRate = 600000)
    public void updateNewsFromRSS() {
        for (String rssFeedUrl : RSS_FEED_URLS) {
            fetchAndSaveNewsFromRSS(rssFeedUrl);
        }
    }

    public NewsPageDto getAllNews(int page, int size, String searchQuery) {
        Page<News> newsPage;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            List<News> searchResult = newsRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    searchQuery,
                    searchQuery);

            List<News> sortedSearchResult = searchResult.stream()
                    .sorted((n1, n2) -> n2.getPubDate().compareTo(n1.getPubDate()))
                    .collect(Collectors.toList());

            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, sortedSearchResult.size());
            List<News> paginatedResult = sortedSearchResult.subList(startIndex, endIndex);

            return new NewsPageDto(paginatedResult, searchResult.size(),
                    calculateTotalPages(searchResult.size(), size));
        } else {
            newsPage = newsRepository.findAll(PageRequest.of(page, size, Sort.by("pubDate").descending()));
        }

        List<News> newsList = newsPage.get().collect(Collectors.toList());
        return new NewsPageDto(newsList, newsPage.getTotalElements(), newsPage.getTotalPages());
    }

    private int calculateTotalPages(int totalItems, int pageSize) {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public News getNewsDetailsBySlug(String slug) {
        return newsRepository.findBySlug(slug).orElse(null);
    }
}