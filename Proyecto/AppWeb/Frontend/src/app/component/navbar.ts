import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
// Asegúrate de que la ruta al servicio de notificaciones sea correcta
import { NotificationService, Notificacion } from '../services/notification.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.scss']
})
export class NavbarComponent implements OnInit {

  // VARIABLES DE USUARIO (Dinámicas para la BD)
  nombreUsuario: string = '';
  rolUsuario: string = '';
  iniciales: string = '';

  // VARIABLES DE NOTIFICACIONES
  showNotifications = false;
  notificaciones: Notificacion[] = [];
  unreadCount = 0;

  constructor(
    private router: Router,
    private notifService: NotificationService
  ) {}

  ngOnInit() {
    this.cargarDatosUsuario();
    this.cargarNotificaciones();
  }

  // --- 1. LÓGICA DE USUARIO ---
  cargarDatosUsuario() {
    // ⚠️ AQUÍ CONECTARÁS TU BASE DE DATOS LUEGO.
    // Por ahora, simulamos que recuperamos los datos del Login (ej. localStorage)

    // Simulación:
    const dataSimulada = {
      nombre: localStorage.getItem('nombre_usuario') || 'Usuario Desconocido',
      rol: localStorage.getItem('rol_usuario') || 'Invitado'
    };

    this.nombreUsuario = dataSimulada.nombre;
    this.rolUsuario = dataSimulada.rol;

    // Extraer la primera letra para el avatar
    this.iniciales = this.nombreUsuario.charAt(0).toUpperCase();
  }

  logout() {
    console.log('Cerrando sesión...');
    // 1. Limpiar almacenamiento local
    localStorage.clear();
    // 2. Redirigir al login
    this.router.navigate(['/login']);
  }

  // --- 2. LÓGICA DE NOTIFICACIONES ---
  cargarNotificaciones() {
    // Detectamos rol por la URL para pruebas, luego usarás this.rolUsuario
    let rolParaNotis = '';
    if (this.router.url.includes('postulante')) rolParaNotis = 'postulante';
    else if (this.router.url.includes('evaluador')) rolParaNotis = 'evaluador';
    else if (this.router.url.includes('admin')) rolParaNotis = 'admin';
    else rolParaNotis = 'postulante'; // Default para pruebas

    this.notificaciones = this.notifService.getNotificaciones(rolParaNotis);
    this.unreadCount = this.notifService.getUnreadCount(rolParaNotis);
  }

  toggleNotifications() {
    this.showNotifications = !this.showNotifications;
  }
}
