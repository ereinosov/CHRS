import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

// AJUSTA ESTAS RUTAS si tu carpeta se llama diferente
import { NavbarComponent } from '../../component/navbar';
import { FooterComponent } from '../../component/footer';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './admin.html',
  styleUrls: ['./admin.scss']
})
export class AdminComponent {

  constructor(private router: Router) {}

  navegarA(ruta: string): void {
    console.log('Navegando a:', ruta);
    // this.router.navigate([`/admin/${ruta}`]);
  }
}
