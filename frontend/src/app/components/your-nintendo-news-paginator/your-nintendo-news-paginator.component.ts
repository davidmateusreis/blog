import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-your-nintendo-news-paginator',
  templateUrl: './your-nintendo-news-paginator.component.html',
  styleUrls: ['./your-nintendo-news-paginator.component.css']
})
export class YourNintendoNewsPaginatorComponent {

  @Input() currentPage: number = 0;
  @Input() totalPages: number = 1;
  @Output() pageChange = new EventEmitter<number>();

  constructor(private router: Router) { }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.pageChange.emit(newPage);
      this.router.navigate(['/page', newPage + 1]);
    }
  }

  onPrevious(): void {
    if (this.currentPage > 0) {
      this.onPageChange(this.currentPage - 1);
    }
  }

  onNext(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.onPageChange(this.currentPage + 1);
    }
  }

  getPageNumbers(): number[] {
    const totalPages = this.totalPages;
    const currentPage = this.currentPage + 1;

    // If there are 4 or fewer pages, return all pages
    if (totalPages <= 4) {
      return Array.from({ length: totalPages }, (_, i) => i + 1);
    }

    // Calculate the starting page to display
    let startPage = currentPage - 1;

    // If the starting page is less than 1, set it to 1
    if (startPage < 1) {
      startPage = 1;
    }

    // If the starting page is more than totalPages - 3, adjust it to display the last 4 pages
    if (startPage > totalPages - 3) {
      startPage = totalPages - 3;
    }

    // Return an array of 4 consecutive page numbers starting from the calculated startPage
    return Array.from({ length: 4 }, (_, i) => startPage + i);
  }
}
