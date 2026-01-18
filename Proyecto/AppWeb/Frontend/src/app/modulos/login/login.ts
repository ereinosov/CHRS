import { Component, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;
  showPassword = false;
  submitted = false;
  serverError = '';

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [
        Validators.required,
        Validators.email,
        this.noSpacesValidator
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(6),
        this.noOnlySpacesValidator
      ]],
      rememberMe: [false]
    });
  }

  // ============ VALIDADORES PERSONALIZADOS ============

  // No permite espacios en blanco
  noSpacesValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) return null;

    if (value.includes(' ')) {
      return { hasSpaces: true };
    }
    return null;
  }

  // No permite contraseña solo con espacios
  noOnlySpacesValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) return null;

    if (value.trim() === '') {
      return { onlySpaces: true };
    }
    return null;
  }

  // ============ GETTERS ============
  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  // ============ MÉTODOS ============

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  // Método para marcar campo como tocado
  markFieldAsTouched(fieldName: string): void {
    const control = this.loginForm.get(fieldName);
    control?.markAsTouched();
    control?.updateValueAndValidity();
  }

  // Retorna el mensaje de error específico para email
  getEmailError(): string {
    const emailControl = this.loginForm.get('email');

    if (!emailControl || !emailControl.errors) {
      return '';
    }

    if (emailControl.hasError('required')) {
      return 'El correo electrónico es obligatorio';
    }
    if (emailControl.hasError('email')) {
      return 'Ingresa un correo electrónico válido';
    }
    if (emailControl.hasError('hasSpaces')) {
      return 'El correo no puede contener espacios en blanco';
    }

    return '';
  }

  // Retorna el mensaje de error específico para password
  getPasswordError(): string {
    const passwordControl = this.loginForm.get('password');

    if (!passwordControl || !passwordControl.errors) {
      return '';
    }

    if (passwordControl.hasError('required')) {
      return 'La contraseña es obligatoria';
    }
    if (passwordControl.hasError('minlength')) {
      const requiredLength = passwordControl.getError('minlength')?.requiredLength;
      return `La contraseña debe tener al menos ${requiredLength} caracteres`;
    }
    if (passwordControl.hasError('onlySpaces')) {
      return 'La contraseña no puede contener solo espacios';
    }

    return '';
  }

  // Método mejorado para marcar todos los campos
  markAllAsTouched(): void {
    Object.keys(this.loginForm.controls).forEach(key => {
      const control = this.loginForm.get(key);
      control?.markAsTouched();
      control?.markAsDirty();
      control?.updateValueAndValidity();
    });
  }

  onSubmit(): void {
    // Primero establecer submitted en true para mostrar errores
    this.submitted = true;
    this.serverError = '';

    // Marcar todos los campos como tocados y actualizar validación
    this.markAllAsTouched();

    // Verificar si el formulario es válido
    if (this.loginForm.invalid) {
      console.log(' Formulario inválido - Mostrando errores...');
      console.log('Email válido:', this.email?.valid);
      console.log('Password válido:', this.password?.valid);
      console.log('Errores email:', this.email?.errors);
      console.log('Errores password:', this.password?.errors);
      return; // Detener aquí para mostrar errores
    }

    // Si llega aquí, el formulario es válido
    console.log(' Formulario válido, procediendo con login...');
    this.isLoading = true;

    const { email, password, rememberMe } = this.loginForm.value;

    console.log('📤 Datos a enviar:', { email, password, rememberMe });

    // Simulación de llamada API
    setTimeout(() => {
      this.isLoading = false;
      console.log('Login exitoso');
      alert('Login exitoso - ' + email);
      // Aquí normalmente redirigirías al usuario
      // this.router.navigate(['/dashboard']);
    }, 1500);
  }
}

class Login {
}

export default Login
