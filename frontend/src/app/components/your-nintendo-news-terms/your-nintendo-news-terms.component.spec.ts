import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsTermsComponent } from './your-nintendo-news-terms.component';

describe('YourNintendoNewsTermsComponent', () => {
  let component: YourNintendoNewsTermsComponent;
  let fixture: ComponentFixture<YourNintendoNewsTermsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourNintendoNewsTermsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
