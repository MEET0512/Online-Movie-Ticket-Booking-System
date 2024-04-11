import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MoviesComponent } from './movies/movies.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    {path:'', component: HomeComponent},
    {path:'home', component:HomeComponent },
    {path:'movies', component: MoviesComponent},
    {path:'login', component: LoginComponent}
];
