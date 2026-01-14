import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TestService } from '../services/test.service';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HttpClientModule,
    CommonModule,
    FormsModule,
    RouterOutlet
  ],
  templateUrl: 'app.component.html'
})
export class AppComponent {
  respuesta: string = '';
  nombre: string = '';
  mensaje: string = '';

  constructor(private testService: TestService) {}

  probarConexion() {
    this.testService.testConection().subscribe({
      next: (data) => {
        this.respuesta = data;
        console.log('Éxito:', data);
      },
      error: (error) => {
        this.respuesta = 'Error en la conexión';
        console.error('Error:', error);
      }
    });
  }

  saludar() {
    this.testService.saludar(this.nombre).subscribe({
      next: (data) => this.respuesta = data,
      error: (error) => console.error(error)
    });
  }

  enviarMensaje() {
    this.testService.enviarMensaje(this.mensaje).subscribe({
      next: (data) => this.respuesta = data,
      error: (error) => console.error(error)
    });
  }
}
