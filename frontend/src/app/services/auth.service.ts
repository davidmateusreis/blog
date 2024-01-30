import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient,
    private jwtHelper: JwtHelperService
  ) { }

  login(credentials: { username: string; password: string }): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}/login`, credentials)
      .pipe(
        tap(response => {
          const token = response.accessToken;
          this.storeToken(token);
        })
      );
  }

  private storeToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getUserRoles(): string[] {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.roles || [];
    }
    return [];
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
