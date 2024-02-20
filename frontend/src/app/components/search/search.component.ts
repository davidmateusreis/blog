import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Subject, debounceTime } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchQuery: string = '';

  @Output() searchEvent = new EventEmitter<string>();

  private searchSubject = new Subject<void>();

  constructor() { }

  ngOnInit(): void {
    this.searchSubject.pipe(debounceTime(300)).subscribe(() => {
      this.emitSearchEvent();
    });
  }

  onSearchInputChange(): void {
    this.searchSubject.next();
  }

  emitSearchEvent(): void {
    this.searchEvent.emit(this.searchQuery.trim());
  }
}
