import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class TestService{
  private apiUrl = 'http://localhost:8080/api';
  constructor(private http: HttpClient) {
  }
  testConection(): Observable<string> {
    return this.http.get(`${this.apiUrl}/test`, { responseType: 'text' })
  }

  saludar(nombre: string): Observable<string>{
    return this.http.get(`${this.apiUrl}/saludo/${nombre}`, { responseType: 'text' })
  }

  enviarMensaje(text: string): Observable<string> {
    return this.http.post(`${this.apiUrl}/mensaje`, { text }, { responseType: 'text' })
  }
}
