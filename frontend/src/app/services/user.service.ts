import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  private createHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  registerNewUser(user: User): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/users/register`, user);
  }

  getAllUsers(): Observable<User[]> {
    const headers = this.createHeaders();
    return this.httpClient.get<User[]>(`${this.baseUrl}/users`, { headers: headers });
  }

  updateUserStatus(id: number): Observable<User> {
    const headers = this.createHeaders();
    return this.httpClient.put<User>(`${this.baseUrl}/users/${id}/status`, {}, { headers: headers });
  }

  getCurrentUser(): Observable<User> {
    const headers = this.createHeaders();
    return this.httpClient.get<User>(`${this.baseUrl}/users/me`, { headers: headers });
  }
}
