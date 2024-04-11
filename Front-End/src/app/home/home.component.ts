import { Component } from '@angular/core';
import { UpcomingMovieBannerComponent } from './upcoming-movie-banner/upcoming-movie-banner.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    UpcomingMovieBannerComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
