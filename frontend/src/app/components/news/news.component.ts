import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NewsPage } from 'src/app/models/news-page.model';
import { News } from 'src/app/models/news.model';
import { NewsService } from 'src/app/services/news.service';
import { debounceTime } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  newsPage: NewsPage = { news: [], totalElements: 0, totalPages: 0 };
  currentPage = 0;
  size = 12;
  searchQuery: string = '';

  @Output() searchEvent = new EventEmitter<string>();

  private searchSubject = new Subject<void>();

  constructor(
    private newsService: NewsService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.currentPage = +params['pageNumber'] - 1 || 0;
      this.loadNews();
    });

    this.searchSubject.pipe(debounceTime(300)).subscribe(() => {
      this.performSearch();
    });
  }

  onSearchInputChange(): void {
    this.searchSubject.next();
  }

  loadNews(): void {
    if (this.searchQuery.trim() !== '') {

      this.newsService.getNews(0, this.size, this.searchQuery)
        .subscribe(
          (response: NewsPage) => {
            this.newsPage = response;
          },
          error => {
            console.log('Error loading news:', error);
          }
        );
    } else {

      this.newsService.getNews(this.currentPage, this.size)
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

  showNewsDetails(news: News) {
    const pubDate = new Date(news.pubDate);
    const year = pubDate.getFullYear();
    const month = (pubDate.getMonth() + 1).toString().padStart(2, '0');
    const slug = news.slug;

    this.router.navigate(['news', year, month, slug]);
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
    this.router.navigate(['/page', newPage + 1]);
  }
}