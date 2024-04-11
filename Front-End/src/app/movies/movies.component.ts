import { Component } from '@angular/core';
import { CardComponent } from '../component/card/card.component';
import { CommonModule } from '@angular/common';
import { ApiService } from '../service/ApiService';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [
    CommonModule,
    CardComponent,
    FormsModule
  ],
  templateUrl: './movies.component.html',
})
export class MoviesComponent {

  activeFilter: string = 'all'; // Default to 'all' when no filter is active

  searchText: string = '';
 
  movies: any = [];

  showedMovies: any =[];

  constructor(private api:ApiService){}

  ngOnInit(): void {
    this.api.getAllMovies().subscribe(data => {
      this.movies = data;
      this.showedMovies = this.movies;
    });
  }

  searcMovies() {
    this.showedMovies = this.movies.filter((movie: any) => {
      return movie.title.toLowerCase().includes(this.searchText.toLowerCase());
    });
  }

  allMovies() {
    if(this.searchText == '') {
      this.showedMovies = this.movies;
    }
  }

  // Method to set the active filter
  setActiveFilter(filter: string) {
    this.activeFilter = filter;
  }
}
