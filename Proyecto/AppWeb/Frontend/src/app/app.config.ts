import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http'
import { routes } from './app.routes'; // Importamos las rutas que definimos arriba

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient() // Aquí le inyectamos las rutas a la aplicación
  ]
};
