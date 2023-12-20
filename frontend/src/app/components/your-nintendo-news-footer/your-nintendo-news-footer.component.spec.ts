import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsFooterComponent } from './your-nintendo-news-footer.component';

describe('YourNintendoNewsFooterComponent', () => {
  let component: YourNintendoNewsFooterComponent;
  let fixture: ComponentFixture<YourNintendoNewsFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [YourNintendoNewsFooterComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
