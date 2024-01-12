import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsContactComponent } from './your-nintendo-news-contact.component';

describe('YourNintendoNewsContactComponent', () => {
  let component: YourNintendoNewsContactComponent;
  let fixture: ComponentFixture<YourNintendoNewsContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourNintendoNewsContactComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
