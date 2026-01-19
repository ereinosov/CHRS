import { Routes } from '@angular/router';
import { AuthGuard } from './services/auth.guard';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () => import('./modulos/login/login').then(m => m.LoginComponent)
  },
  {
    path: 'postulante',
    loadComponent: () => import('./modulos/Postulante/postulante').then(m => m.PostulanteComponent),
    canActivate: [AuthGuard],
    data: { rol: 'postulante' }
  },
  {
    path: 'evaluador',
    loadComponent: () => import('./modulos/Evaluador/evaluador').then(m => m.EvaluadorComponent),
    canActivate: [AuthGuard],
    data: { rol: 'evaluador' }
  },
  {
    path: 'admin',
    loadComponent: () => import('./modulos/Admin/admin').then(m => m.AdminComponent),
    canActivate: [AuthGuard],
    data: { rol: 'admin' }
  },
  {
    path: '**',
    redirectTo: 'login'
  }

];
