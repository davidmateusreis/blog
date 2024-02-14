import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  submitted = false;

  modalMessage = '';
  showModal = false;

  loading: boolean = false;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email, Validators.minLength(4), Validators.maxLength(40)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]]
    });
  }

  registerNewUser() {
    if (this.registerForm.valid) {
      this.submitted = true;
      this.loading = true;

      const formData = this.registerForm.value;

      this.userService.registerNewUser(formData).subscribe(
        (response) => {
          this.modalMessage = 'Your account has been created successfully!';
          this.showModal = true;
        },
        (error) => {
          this.modalMessage = error.error.message;
          this.showModal = true;
          this.registerForm.reset();
        }
      ).add(() => {
        this.loading = false;
      });
    }
  }

  onCloseModal() {
    this.router.navigate(['/login']);
  }
}
