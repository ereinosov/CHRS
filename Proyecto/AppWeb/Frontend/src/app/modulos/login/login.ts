import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Importante para ngModel si lo usas

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.scss']
})
export class LoginComponent {
  showPassword = false;

  constructor(private router: Router) {}

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  onLogin() {
    // Simulamos que el backend nos devolvió esto
    localStorage.setItem('nombre_usuario', 'Joseph Calderón');
    localStorage.setItem('rol_usuario', 'Postulante');

    // Redirigir
    this.router.navigate(['/admin']);
  }
}
