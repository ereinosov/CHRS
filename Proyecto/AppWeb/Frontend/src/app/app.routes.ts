import { Routes } from '@angular/router';
import { LoginComponent } from './login/login'; // Verifica que la ruta al archivo sea correcta

export const routes: Routes = [
  // Esta línea es la que hace la magia: si la URL está vacía, te manda a /login
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent }
];
