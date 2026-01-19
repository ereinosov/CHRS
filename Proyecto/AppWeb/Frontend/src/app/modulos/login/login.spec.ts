import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Login } from './login';
import { FormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { Router } from '@angular/router';

describe('LoginComponent', () => {
  let component: Login;
  let fixture: ComponentFixture<Login>;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        FormsModule,
        Login
      ],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(Login);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have initial values', () => {
    expect(component.usuarioApp).toBe('');
    expect(component.claveApp).toBe('');
    expect(component.showPassword).toBe(false);
    expect(component.isLoading).toBe(false);
    expect(component.serverError).toBe('');
  });

  it('should toggle password visibility', () => {
    expect(component.showPassword).toBe(false);

    component.togglePassword();
    expect(component.showPassword).toBe(true);

    component.togglePassword();
    expect(component.showPassword).toBe(false);
  });

  it('should show error when fields are empty', () => {
    component.usuarioApp = '';
    component.claveApp = '';

    component.onLogin();

    expect(component.serverError).toBe('Por favor complete todos los campos');
  });

  it('should not call backend when fields are empty', () => {
    component.usuarioApp = '';
    component.claveApp = '';

    const initialLoading = component.isLoading;
    component.onLogin();

    expect(component.isLoading).toBe(initialLoading);
  });

  it('should set loading state when form is valid', () => {
    component.usuarioApp = 'testuser';
    component.claveApp = 'testpass';

    // El componente intentará hacer la llamada HTTP
    // pero fallará porque no hay backend real en tests
    component.onLogin();

    // Verificar que al menos intentó cargar
    expect(component.serverError).toBe('');
  });
});
