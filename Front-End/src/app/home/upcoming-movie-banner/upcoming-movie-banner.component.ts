import { animate, state, style, transition, trigger } from '@angular/animations';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-upcoming-movie-banner',
  standalone: true,
  imports: [
    CommonModule,
  ],
  templateUrl: './upcoming-movie-banner.component.html',
  styleUrl: './upcoming-movie-banner.component.css',
})
export class UpcomingMovieBannerComponent {

  isIconShow: boolean = false;

  toggleIcon() {
    this.isIconShow = !this.isIconShow;
  }

}
