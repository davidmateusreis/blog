import { Component, OnInit } from '@angular/core';
import { NewsPage } from 'src/app/models/news-page.model';
import { YourNintendoNewsService } from 'src/app/services/your-nintendo-news.service';

@Component({
  selector: 'app-your-nintendo-news-news',
  templateUrl: './your-nintendo-news-news.component.html',
  styleUrls: ['./your-nintendo-news-news.component.css']
})
export class YourNintendoNewsNewsComponent implements OnInit {

  newsPage: NewsPage = { news: [], totalElements: 0, totalPages: 0 };
  currentPage = 0;
  size = 6;

  constructor(private yourNintendoNewsService: YourNintendoNewsService) { }

  ngOnInit(): void {
    this.loadNews();
  }

  loadNews(): void {
    this.yourNintendoNewsService.getNews(this.currentPage, this.size)
      .subscribe(
        (response: NewsPage) => {
          this.newsPage = response;
        },
        error => {
          console.log('Error loading news:', error);
        }
      );
  }

  onPageChange(newPage: number): void {
    this.currentPage = newPage;
    this.loadNews();
  }

  removeLastParagraph(text: string): string {

    const lastIndex = text.lastIndexOf('<p>');

    if (lastIndex !== -1) {
      const openingTagEndIndex = lastIndex + 2;
      const closingTagStartIndex = text.indexOf('</p>', openingTagEndIndex);

      if (closingTagStartIndex !== -1) {
        return text.substring(0, lastIndex) + text.substring(closingTagStartIndex + 4);
      }
    }

    return text;
  }
}
