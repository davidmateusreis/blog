import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient,
    private router: Router
  ) {
    this.contactForm = this.formBuilder.group({
      messageAuthor: [''],
      messageEmail: [''],
      messageContent: ['']
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.contactForm.valid) {

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
