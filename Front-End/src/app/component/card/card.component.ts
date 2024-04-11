import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './card.component.html',
})
export class CardComponent {

  @Input() cardTitle: String = '';
  @Input() cardDescription: String = '';
  @Input() cardImg: String = '';
}
