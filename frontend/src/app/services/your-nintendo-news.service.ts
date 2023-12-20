import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class YourNintendoNewsService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  getNews(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.baseUrl}`);
  }
}
