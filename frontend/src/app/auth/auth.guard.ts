import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router,
    private jwtHelper: JwtHelperService,
    private authService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const token = localStorage.getItem('token');

    if (token && !this.jwtHelper.isTokenExpired(token)) {
      const userRoles = this.authService.getUserRoles();

      const requiredRoles = route.data['roles'] as Array<string>;

      for (const requiredRole of requiredRoles) {
        if (userRoles.includes(requiredRole)) {
          return true;
        }
      }
    }

    this.router.navigate(['/login']);
    return false;
  }
}
