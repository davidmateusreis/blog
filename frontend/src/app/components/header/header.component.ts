import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { DarkModeService } from 'src/app/services/dark-mode.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public darkModeService: DarkModeService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  toggleDarkMode(): void {
    this.darkModeService.toggleDarkMode();
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  hasAdminRole(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles.includes('ROLE_ADMIN');
  }

  hasUserRole(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles.includes('ROLE_USER');
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
