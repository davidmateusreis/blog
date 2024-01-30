import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NewsPage } from '../models/news-page.model';
import { News } from '../models/news.model';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  getNews(page: number, size: number, searchQuery?: string): Observable<NewsPage> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (searchQuery) {
      params = params.set('searchQuery', searchQuery);
    }

    return this.httpClient.get<NewsPage>(`${this.baseUrl + '/news'}`, { params });
  }

  public getNewsDetailsBySlug(slug: string) {
    return this.httpClient.get<News>(`${this.baseUrl}/news/${slug}`);
  }
}
