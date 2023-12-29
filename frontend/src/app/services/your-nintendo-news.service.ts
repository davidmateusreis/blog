import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NewsPage } from '../models/news-page.model';

@Injectable({
  providedIn: 'root'
})
export class YourNintendoNewsService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  getNews(page: number, size: number): Observable<NewsPage> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.httpClient.get<NewsPage>(`${this.baseUrl}`, { params });
  }
}
