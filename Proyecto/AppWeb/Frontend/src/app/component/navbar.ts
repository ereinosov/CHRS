import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  // CORRECCIÓN: Tu archivo se llama 'navbar.html', no 'navbar.component.html'
  templateUrl: './navbar.html',
  // CORRECCIÓN: Tu archivo se llama 'navbar.scss', no 'navbar.component.scss'
  styleUrls: ['./navbar.scss']
})
export class NavbarComponent {}
