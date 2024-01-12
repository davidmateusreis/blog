import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsNewsDetailsComponent } from './your-nintendo-news-news-details.component';

describe('YourNintendoNewsNewsDetailsComponent', () => {
  let component: YourNintendoNewsNewsDetailsComponent;
  let fixture: ComponentFixture<YourNintendoNewsNewsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourNintendoNewsNewsDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsNewsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
