import { LoginService } from './../login/login.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Water } from 'src/app/components/models/water';

@Injectable({
  providedIn: 'root'
})
export class WaterService {
  baseUrl = 'http://localhost:8080/water'
  constructor(private http: HttpClient, private userService: LoginService) { }

  findAll(token: string): Observable<Water[]> {
    let ids= null;
    ids =this.userService.findByIdUser(token)
    return this.http.get<Water[]>(`${this.baseUrl}/total/${ids}`);
  }

  create(water: Water) : Observable<Water>{
    return this.http.post<Water>(`${this.baseUrl}`, water);
  }

  findById(id: string): Observable<Water> {
    return this.http.get<Water>(`${this.baseUrl}/${id}`);
  }

  update(water: Water) : Observable<Water> {
    return this.http.put<Water>(`${this.baseUrl}/${water.id}`, water);
  }

  delete(id: string): Observable<Water> {
    return this.http.delete<Water>(`${this.baseUrl}/${id}`);
  }
}
