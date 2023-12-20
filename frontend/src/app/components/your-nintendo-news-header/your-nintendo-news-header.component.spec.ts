import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsHeaderComponent } from './your-nintendo-news-header.component';

describe('YourNintendoNewsHeaderComponent', () => {
  let component: YourNintendoNewsHeaderComponent;
  let fixture: ComponentFixture<YourNintendoNewsHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [YourNintendoNewsHeaderComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
