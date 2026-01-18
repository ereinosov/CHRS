import { Routes } from '@angular/router';
import { LoginComponent } from './modulos/login/login';
import { TestConexionComponent } from './test-conexion/test-conexion';

export const routes: Routes = [
  // Te lleva directo a la prueba
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Ruta para probar la conexión
  { path: 'test', component: TestConexionComponent },

  // Tu login
  { path: 'login', component: LoginComponent }
];
