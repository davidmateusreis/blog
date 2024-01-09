import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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
  size = 12;
  searchQuery: string = '';

  @Output() searchEvent = new EventEmitter<string>();

  constructor(private yourNintendoNewsService: YourNintendoNewsService) { }

  ngOnInit(): void {
    this.loadNews();
  }

  loadNews(): void {
    // Check if a search query is present
    if (this.searchQuery.trim() !== '') {
      // If search query is present, fetch search results
      this.yourNintendoNewsService.getNews(0, this.size, this.searchQuery)
        .subscribe(
          (response: NewsPage) => {
            this.newsPage = response;
          },
          error => {
            console.log('Error loading news:', error);
          }
        );
    } else {
      // If no search query, fetch regular news
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
  }

  getAuthorColor(author: string): string {
    switch (author.toLowerCase()) {
      case 'nintendo life':
        return '#d80108';
      case 'push square':
        return '#094da4';
      case 'pure xbox':
        return '#5cb91f';
      default:
        return '#d80108';
    }
  }

  getAuthorLink(author: string): string {
    switch (author.toLowerCase()) {
      case 'nintendo life':
        return 'http://www.nintendolife.com/';
      case 'push square':
        return 'http://www.pushsquare.com/';
      case 'pure xbox':
        return 'http://www.purexbox.com/';
      default:
        return '';
    }
  }

  performSearch() {
    this.loadNews();
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
