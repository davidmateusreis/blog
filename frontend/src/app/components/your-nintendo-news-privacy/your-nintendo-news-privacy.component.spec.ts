import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsPrivacyComponent } from './your-nintendo-news-privacy.component';

describe('YourNintendoNewsPrivacyComponent', () => {
  let component: YourNintendoNewsPrivacyComponent;
  let fixture: ComponentFixture<YourNintendoNewsPrivacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourNintendoNewsPrivacyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsPrivacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
