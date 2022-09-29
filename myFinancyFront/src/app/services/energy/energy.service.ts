
import { LoginService } from './../login/login.service';
import { Energy } from './../../components/models/energy';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EnergyService {
  reload=true;
  baseUrl = 'http://localhost:8080/energy'
  constructor(private http: HttpClient, private userService: LoginService) { }

  findAll(token: string): Observable<Energy[]> {
    let ids= null;
    ids =this.userService.findByIdUser(token)
    return this.http.get<Energy[]>(`${this.baseUrl}/total/${ids}`);
  }

  create(energy: Energy) : Observable<Energy>{
    return this.http.post<Energy>(`${this.baseUrl}`, energy);
  }

  findById(id: string): Observable<Energy> {
    return this.http.get<Energy>(`${this.baseUrl}/${id}`);
  }

  update(energy: Energy) : Observable<Energy> {
    return this.http.put<Energy>(`${this.baseUrl}/${energy.id}`, energy);
  }

  delete(id: string): Observable<Energy> {
    return this.http.delete<Energy>(`${this.baseUrl}/${id}`);
  }

}
