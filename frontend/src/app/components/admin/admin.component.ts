import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[] = [];

  loading: boolean = false;
  apiError: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.loading = true;

    this.userService.getAllUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        this.loading = false;
      },
      error => {
        console.error('Error fetching users:', error);
        this.loading = false;
        this.showErrorMessage();
      }
    );
  }

  updateUserStatus(id: number): void {
    this.userService.updateUserStatus(id).subscribe(
      (user: User) => {
        console.log('User status successfully updated!', user);
      },
      (error) => {
        console.error('Error toggling user status:', error);
      }
    );
  }

  showErrorMessage(): void {
    this.apiError = true;
  }
}