import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  login(credentials: { username: string; password: string }): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}/login`, credentials)
      .pipe(
        tap(response => {
          const token = response.accessToken;
          const roles = response.roles;
          const active = response.active;
          this.storeToken(token);
          this.storeRoles(roles);
          this.storeUserStatus(active);
        })
      );
  }

  private storeToken(token: string): void {
    localStorage.setItem('token', token);
  }

  private storeRoles(roles: string[]): void {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  private storeUserStatus(active: boolean): void {
    localStorage.setItem('userStatus', active.toString());
  }

  getUserRoles(): string[] {
    const rolesString = localStorage.getItem('roles');
    return rolesString ? JSON.parse(rolesString) : [];
  }

  getUserStatus(): boolean {
    return localStorage.getItem('userStatus') === 'true';
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
    localStorage.removeItem('userStatus');
  }
}
