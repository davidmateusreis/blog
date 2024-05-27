import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.css']
})
export class ActivateComponent implements OnInit {

  message: string = '';

  loading: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private httpClient: HttpClient,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      if (token) {
        this.activateAccount(token);
      }
    });
  }

  activateAccount(token: string): void {

    this.loading = true;

    const apiUrl = `${environment.apiUrl}/activate?token=${token}`;

    this.httpClient.get(apiUrl, { responseType: 'text' }).subscribe(
      (response) => {
        this.message = 'Your account activated successfully. Redirecting to login page...';
        this.loading = false;
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 6000);
      },
      (error) => {
        this.message = 'Invalid or expired token.';
        this.loading = false;
      }
    );
  }
}
