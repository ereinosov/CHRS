import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface Notificacion {
  id: number;
  titulo: string;
  mensaje: string;
  hora: string;
  leido: boolean;
  tipo: 'info' | 'warning' | 'success'; // Para cambiar el color del icono
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  // Simulamos datos para cada rol
  private notificacionesPostulante: Notificacion[] = [
    { id: 1, titulo: 'Entrevista Agendada', mensaje: 'Tu entrevista técnica es mañana a las 10:00 AM.', hora: 'Hace 1h', leido: false, tipo: 'success' },
    { id: 2, titulo: 'Documento Faltante', mensaje: 'Por favor sube tu certificado de votación.', hora: 'Hace 3h', leido: false, tipo: 'warning' }
  ];

  private notificacionesEvaluador: Notificacion[] = [
    { id: 3, titulo: 'Nueva Postulación', mensaje: 'Juan Pérez ha enviado sus documentos.', hora: 'Hace 10min', leido: false, tipo: 'info' },
    { id: 4, titulo: 'Evaluación Pendiente', mensaje: 'Tienes 3 carpetas por revisar hoy.', hora: 'Hace 5h', leido: true, tipo: 'warning' }
  ];

  private notificacionesAdmin: Notificacion[] = [
    { id: 5, titulo: 'Sistema Actualizado', mensaje: 'Mantenimiento programado para el viernes.', hora: 'Hace 1d', leido: true, tipo: 'info' }
  ];

  constructor() { }

  // Método para obtener notificaciones según el rol (Simulado por URL)
  getNotificaciones(rol: string): Notificacion[] {
    if (rol.includes('postulante')) return this.notificacionesPostulante;
    if (rol.includes('evaluador')) return this.notificacionesEvaluador;
    if (rol.includes('admin')) return this.notificacionesAdmin;
    return [];
  }

  // Contar no leídas
  getUnreadCount(rol: string): number {
    return this.getNotificaciones(rol).filter(n => !n.leido).length;
  }
}
