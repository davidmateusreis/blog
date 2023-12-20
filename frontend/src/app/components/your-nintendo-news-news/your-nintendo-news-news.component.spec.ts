import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsNewsComponent } from './your-nintendo-news-news.component';

describe('YourNintendoNewsNewsComponent', () => {
  let component: YourNintendoNewsNewsComponent;
  let fixture: ComponentFixture<YourNintendoNewsNewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [YourNintendoNewsNewsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
