import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-your-nintendo-news-paginator',
  templateUrl: './your-nintendo-news-paginator.component.html',
  styleUrls: ['./your-nintendo-news-paginator.component.css']
})
export class YourNintendoNewsPaginatorComponent {

  @Input() currentPage: number = 0;
  @Input() totalPages: number = 1;
  @Output() pageChange = new EventEmitter<number>();

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.pageChange.emit(newPage);
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
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }
}
