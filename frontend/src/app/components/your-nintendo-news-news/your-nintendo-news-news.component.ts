import { Component, OnInit } from '@angular/core';
import { YourNintendoNewsService } from 'src/app/services/your-nintendo-news.service';

@Component({
  selector: 'app-your-nintendo-news-news',
  templateUrl: './your-nintendo-news-news.component.html',
  styleUrls: ['./your-nintendo-news-news.component.css']
})
export class YourNintendoNewsNewsComponent implements OnInit {

  newsList: any[] = [];

  constructor(private yourNintendoNewsService: YourNintendoNewsService) { }

  ngOnInit(): void {
    this.loadNews();
  }

  loadNews(): void {
    this.yourNintendoNewsService.getNews().subscribe((data) => {
      this.newsList = data;
    });
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
