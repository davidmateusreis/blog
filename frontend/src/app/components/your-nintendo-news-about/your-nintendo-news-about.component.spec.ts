import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsAboutComponent } from './your-nintendo-news-about.component';

describe('YourNintendoNewsAboutComponent', () => {
  let component: YourNintendoNewsAboutComponent;
  let fixture: ComponentFixture<YourNintendoNewsAboutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourNintendoNewsAboutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsAboutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
