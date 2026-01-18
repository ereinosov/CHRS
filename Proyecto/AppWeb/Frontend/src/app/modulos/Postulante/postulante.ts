import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

// Verifica la ruta de tus componentes compartidos
import { NavbarComponent } from '../../component/navbar';
import { FooterComponent } from '../../component/footer';

@Component({
  selector: 'app-postulante',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './postulante.html',
  styleUrls: ['./postulante.scss']
})
export class PostulanteComponent {

  constructor(private router: Router) {}

  navegarA(ruta: string): void {
    console.log('Navegando a:', ruta);
    // this.router.navigate([`/postulante/${ruta}`]);
  }
}
