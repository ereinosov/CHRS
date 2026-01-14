import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TestService } from '../services/test.service';

@Component({
  selector: 'app-test-conexion',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  templateUrl: './test-conexion.html',
  styleUrls: ['./test-conexion.scss']
})
export class TestConexionComponent {
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
