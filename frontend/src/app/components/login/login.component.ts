import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]]
    });
  }

  login() {
    if (this.loginForm.valid) {
      this.submitted = true;

      const formData = this.loginForm.value;

      this.authService.login(formData).subscribe(
        (response) => {
          alert('Login successfully!');

          const userRoles = this.authService.getUserRoles();

          if (userRoles.includes('Admin')) {
            this.router.navigate(['/admin']);
          } else if (userRoles.includes('User')) {
            this.router.navigate(['/user']);
          } else {
            this.router.navigate(['/']);
          }
        },
        (error) => {
          alert('Login failed!');
          this.loginForm.reset();
        }
      );
    }
  }
}


