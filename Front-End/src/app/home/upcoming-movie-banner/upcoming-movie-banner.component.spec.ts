import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcomingMovieBannerComponent } from './upcoming-movie-banner.component';

describe('UpcomingMovieBannerComponent', () => {
  let component: UpcomingMovieBannerComponent;
  let fixture: ComponentFixture<UpcomingMovieBannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpcomingMovieBannerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpcomingMovieBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
