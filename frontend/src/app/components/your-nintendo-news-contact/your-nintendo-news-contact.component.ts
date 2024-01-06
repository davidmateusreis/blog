import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-your-nintendo-news-contact',
  templateUrl: './your-nintendo-news-contact.component.html',
  styleUrls: ['./your-nintendo-news-contact.component.css']
})
export class YourNintendoNewsContactComponent implements OnInit {

  private baseUrl = environment.apiUrl;

  contactForm!: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private router: Router
  ) {
    this.contactForm = this.formBuilder.group({
      messageAuthor: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
      messageEmail: ['', [Validators.required, Validators.email, Validators.minLength(4), Validators.maxLength(40)]],
      messageContent: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(2000)]]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.contactForm.valid) {

      this.submitted = true;

      const formData = this.contactForm.value;

      this.httpClient.post(`${this.baseUrl + '/contact'}`, formData).subscribe(
        response => {
          alert('Thanks for your feedback and support!');
          this.router.navigate(['/']);
        },
        error => {
          alert('Error sending message!');
        }
      );
    }
  }
}
