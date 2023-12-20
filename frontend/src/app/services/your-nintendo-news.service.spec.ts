import { TestBed } from '@angular/core/testing';

import { YourNintendoNewsService } from './your-nintendo-news.service';

describe('YourNintendoNewsService', () => {
  let service: YourNintendoNewsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(YourNintendoNewsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
