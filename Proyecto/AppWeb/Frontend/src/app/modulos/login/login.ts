import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.scss']
})
export class LoginComponent {
  usuarioApp: string = '';
  claveApp: string = '';
  showPassword = false;
  isLoading = false;
  serverError = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  togglePassword() { this.showPassword = !this.showPassword; }

  onLogin() {
    this.isLoading = true;
    this.serverError = '';

    const timeoutId = setTimeout(() => {
      if (this.isLoading) {
        this.isLoading = false;
        this.serverError = 'El servidor no responde.';
      }
    }, 5000);

    this.authService.login(this.usuarioApp, this.claveApp).subscribe({
      next: (res: any) => {
        clearTimeout(timeoutId);
        this.isLoading = false;

        if (res.success) {
          console.log('Login exitoso:', res);

          // 1. ASIGNAMOS EL ROL MANUALMENTE (Porque la BD devuelve null)
          if (res.id_rol === 3) {
            res.rol = 'admin';
          } else if (res.id_rol === 2) {
            res.rol = 'postulante';
          } else if (res.id_rol === 1) {
            res.rol = 'evaluador';
          }

          // 🔥 CORRECCIÓN CRUCIAL 🔥
          // Usamos tu función del servicio que guarda 'rol', 'usuario' e 'id' por separado.
          // Así el AuthGuard podrá encontrar localStorage.getItem('rol')
          this.authService.guardarSesion(res);

          // 3. REDIRECCIÓN
          switch (res.id_rol) {
            case 3:
              this.router.navigate(['/admin']);
              break;
            case 2:
              this.router.navigate(['/postulante']);
              break;
            case 1:
              this.router.navigate(['/evaluador']);
              break;
            default:
              this.serverError = 'Rol no identificado';
          }
        } else {
          this.serverError = res.error || 'Credenciales incorrectas';
        }
      },
      error: (err) => {
        this.isLoading = false;
        clearTimeout(timeoutId);
        this.serverError = 'Error de conexión o datos incorrectos.';
      }
    });
  }
}
