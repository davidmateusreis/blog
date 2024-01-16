import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { News } from 'src/app/models/news.model';
import { YourNintendoNewsService } from 'src/app/services/your-nintendo-news.service';

@Component({
  selector: 'app-your-nintendo-news-news-details',
  templateUrl: './your-nintendo-news-news-details.component.html',
  styleUrls: ['./your-nintendo-news-news-details.component.css']
})
export class YourNintendoNewsNewsDetailsComponent implements OnInit {

  id!: number;
  newsDetails$!: Observable<News>;

  constructor(
    private yourNintendoNewsService: YourNintendoNewsService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;
    this.newsDetails$ = this.yourNintendoNewsService.getNewsDetailsById(this.id);
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

  getFacebookShareLink(newsDetails: News): string {
    return `https://www.facebook.com/sharer/sharer.php?u=${encodeURIComponent(newsDetails.link)}`;
  }

  getTwitterShareLink(newsDetails: News): string {
    return `https://twitter.com/intent/tweet?url=${encodeURIComponent(newsDetails.link)}&text=${encodeURIComponent(newsDetails.title)}`;
  }

  getRedditShareLink(newsDetails: News): string {
    const redditTitle = encodeURIComponent(newsDetails.title);
    return `https://www.reddit.com/submit?url=${encodeURIComponent(newsDetails.link)}&title=${redditTitle}`;
  }

  getWhatsAppShareLink(newsDetails: News): string {
    return `https://api.whatsapp.com/send?text=${encodeURIComponent(`Check out this news: ${newsDetails.title} ${newsDetails.link}`)}`;
  }
}
