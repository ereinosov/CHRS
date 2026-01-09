import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login';
import { ReactiveFormsModule } from '@angular/forms';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        LoginComponent
      ]
    }).compileComponents();
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a form with email and password controls', () => {
    expect(component.loginForm.contains('email')).toBeTruthy();
    expect(component.loginForm.contains('password')).toBeTruthy();
  });

  it('should make email control required', () => {
    const emailControl = component.loginForm.get('email');
    emailControl?.setValue('');
    expect(emailControl?.valid).toBeFalsy();
  });

  it('should validate email format', () => {
    const emailControl = component.loginForm.get('email');

    emailControl?.setValue('correo-invalido');
    expect(emailControl?.valid).toBeFalsy();

    emailControl?.setValue('correo@valido.com');
    expect(emailControl?.valid).toBeTruthy();
  });

  it('should require password', () => {
    const passwordControl = component.loginForm.get('password');
    passwordControl?.setValue('');
    expect(passwordControl?.valid).toBeFalsy();
  });

  it('should validate password length', () => {
    const passwordControl = component.loginForm.get('password');

    passwordControl?.setValue('123');
    expect(passwordControl?.valid).toBeFalsy();

    passwordControl?.setValue('123456');
    expect(passwordControl?.valid).toBeTruthy();
  });

  it('should not contain spaces in email', () => {
    const emailControl = component.loginForm.get('email');

    emailControl?.setValue('correo con espacios@mail.com');
    expect(emailControl?.hasError('hasSpaces')).toBeTruthy();

    emailControl?.setValue('correosin espacios@mail.com');
    expect(emailControl?.hasError('hasSpaces')).toBeFalsy();
  });

  it('should not accept password with only spaces', () => {
    const passwordControl = component.loginForm.get('password');

    passwordControl?.setValue('     ');
    expect(passwordControl?.hasError('onlySpaces')).toBeTruthy();

    passwordControl?.setValue('contraseña123');
    expect(passwordControl?.hasError('onlySpaces')).toBeFalsy();
  });

  it('should show errors when form is invalid on submit', () => {
    // Simular formulario vacío
    component.loginForm.setValue({
      email: '',
      password: '',
      rememberMe: false
    });

    component.onSubmit();

    expect(component.submitted).toBeTruthy();
    expect(component.loginForm.valid).toBeFalsy();
  });

  it('should not submit when form is invalid', () => {
    // Reemplazar el spyOn por una verificación directa
    component.loginForm.setValue({
      email: '',
      password: '',
      rememberMe: false
    });

    // Guardar estado inicial
    const initialIsLoading = component.isLoading;

    component.onSubmit();

    // Verificar que isLoading no cambió (no se envió)
    expect(component.isLoading).toBe(initialIsLoading);
  });

  it('should submit when form is valid', () => {
    // Configurar formulario válido
    component.loginForm.setValue({
      email: 'test@uteq.edu.ec',
      password: 'password123',
      rememberMe: false
    });

    component.onSubmit();

    // Verificar que isLoading se activó (se intentó enviar)
    expect(component.isLoading).toBeTruthy();
  });

  it('should toggle password visibility', () => {
    const initialVisibility = component.showPassword;

    component.togglePassword();

    expect(component.showPassword).toBe(!initialVisibility);

    component.togglePassword();

    expect(component.showPassword).toBe(initialVisibility);
  });

  it('should mark field as touched when blur event occurs', () => {
    const emailControl = component.loginForm.get('email');
    const initialTouched = emailControl?.touched;

    component.markFieldAsTouched('email');

    expect(emailControl?.touched).toBeTruthy();
  });

  it('should return correct email error messages', () => {
    const emailControl = component.loginForm.get('email');

    emailControl?.setValue('');
    emailControl?.markAsTouched();
    expect(component.getEmailError()).toContain('obligatorio');

    emailControl?.setValue('correo-invalido');
    expect(component.getEmailError()).toContain('válido');

    emailControl?.setValue('correo con espacios@mail.com');
    expect(component.getEmailError()).toContain('espacios');
  });

  it('should return correct password error messages', () => {
    const passwordControl = component.loginForm.get('password');

    passwordControl?.setValue('');
    passwordControl?.markAsTouched();
    expect(component.getPasswordError()).toContain('obligatoria');

    passwordControl?.setValue('123');
    expect(component.getPasswordError()).toContain('al menos 6 caracteres');

    passwordControl?.setValue('     ');
    expect(component.getPasswordError()).toContain('solo espacios');
  });
});
