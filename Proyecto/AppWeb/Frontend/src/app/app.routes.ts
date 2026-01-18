import { Routes } from '@angular/router';

// 1. IMPORTACIONES DE TUS COMPONENTES
// Fíjate bien en las mayúsculas/minúsculas de tus carpetas (Admin, login, Postulante...)
import { LoginComponent } from './modulos/login/login';
import { AdminComponent } from './modulos/Admin/admin';
import { EvaluadorComponent } from './modulos/Evaluador/evaluador';
import { PostulanteComponent } from './modulos/Postulante/postulante';
import { TestConexionComponent } from './test-conexion/test-conexion';

export const routes: Routes = [
  // --- REDIRECCIÓN INICIAL ---
  // Cuando abres la app, te manda aquí. (Cámbialo a 'admin', 'evaluador' o 'postulante' si quieres probar directo)
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // --- RUTAS DE LA APP ---
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'evaluador', component: EvaluadorComponent },
  { path: 'postulante', component: PostulanteComponent },

  // --- RUTA DE PRUEBA (Opcional) ---
  { path: 'test', component: TestConexionComponent }
];
