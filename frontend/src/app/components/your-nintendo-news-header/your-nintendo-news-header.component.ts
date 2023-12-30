import { Component, OnInit } from '@angular/core';
import { DarkModeService } from 'src/app/services/dark-mode.service';

@Component({
  selector: 'app-your-nintendo-news-header',
  templateUrl: './your-nintendo-news-header.component.html',
  styleUrls: ['./your-nintendo-news-header.component.css']
})
export class YourNintendoNewsHeaderComponent implements OnInit {

  constructor(public darkModeService: DarkModeService) { }

  toggleDarkMode(): void {
    this.darkModeService.toggleDarkMode();
  }

  ngOnInit(): void {
  }

}
