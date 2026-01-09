import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes'; // Importamos las rutas que definimos arriba

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes) // Aquí le inyectamos las rutas a la aplicación
  ]
};
