import { Component, OnInit } from '@angular/core';
import { DarkModeService } from 'src/app/services/dark-mode.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public darkModeService: DarkModeService) { }

  toggleDarkMode(): void {
    this.darkModeService.toggleDarkMode();
  }

  ngOnInit(): void {
  }

}
