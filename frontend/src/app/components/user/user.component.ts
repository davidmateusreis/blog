import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService) { }

  currentUser!: User;

  loading: boolean = false;
  apiError: boolean = false;

  ngOnInit(): void {
    this.loadCurrentUser();
  }

  loadCurrentUser(): void {
    this.loading = true;

    this.userService.getCurrentUser().subscribe(
      (user: User) => {
        this.currentUser = user;
        this.loading = false;
      },
      (error) => {
        console.error('Error fetching current user:', error);
        this.loading = false;
        this.showErrorMessage();
      }
    );
  }

  showErrorMessage(): void {
    this.apiError = true;
  }
}
