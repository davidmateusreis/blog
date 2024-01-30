import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  registerNewUser(user: User): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/register`, user);
  }
}
