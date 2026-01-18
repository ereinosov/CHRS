import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EvaluadorComponent } from './evaluador';
import { RouterTestingModule } from '@angular/router/testing'; // Importante para probar rutas

describe('EvaluadorComponent', () => {
  let component: EvaluadorComponent;
  let fixture: ComponentFixture<EvaluadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvaluadorComponent ],
      imports: [ RouterTestingModule ] // Mock del Router
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EvaluadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a dashboard header', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.dashboard-header h2')?.textContent).toContain('Sistema de Selección Docente');
  });
});
