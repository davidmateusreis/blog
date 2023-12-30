import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourNintendoNewsPaginatorComponent } from './your-nintendo-news-paginator.component';

describe('YourNintendoNewsPaginatorComponent', () => {
  let component: YourNintendoNewsPaginatorComponent;
  let fixture: ComponentFixture<YourNintendoNewsPaginatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YourNintendoNewsPaginatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YourNintendoNewsPaginatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
