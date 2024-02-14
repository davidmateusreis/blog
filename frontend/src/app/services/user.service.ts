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
    return this.httpClient.post(`${this.baseUrl}/users/register`, user);
  }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseUrl}/users`);
  }

  updateUserStatus(username: string): Observable<User> {
    return this.httpClient.put<User>(`${this.baseUrl}/users/${username}/status`, {});
  }
}
