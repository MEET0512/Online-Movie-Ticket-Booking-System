import { CommonModule } from '@angular/common';
import { Component, HostListener } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent {

  isSticky = false;

  constructor(private router : Router){}

  @HostListener('window:scroll', ['$event'])
  onScroll() {
    const scrollPostion = window.pageYOffset;
    this.isSticky = scrollPostion > 0;
  }

  navigateToLogin() {
    const url = '/login';
    this.router.navigateByUrl(url);
  }
}
