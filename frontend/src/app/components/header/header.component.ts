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

  toggleDarkMode(): void {
    this.darkModeService.toggleDarkMode();
  }

  ngOnInit(): void {
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  hasAdminRole(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles.includes('Admin');
  }

  hasUserRole(): boolean {
    const userRoles = this.authService.getUserRoles();
    return userRoles.includes('User');
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
