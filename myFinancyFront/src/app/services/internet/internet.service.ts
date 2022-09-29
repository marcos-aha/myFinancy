import { LoginService } from 'src/app/services/login/login.service';
import { Internet } from './../../components/models/internet';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InternetService {
  baseUrl = 'http://localhost:8080/internet'
  constructor(private http: HttpClient, private userService: LoginService) { }

  findAll(token: string): Observable<Internet[]> {
    let ids= null;
    ids =this.userService.findByIdUser(token)
    return this.http.get<Internet[]>(`${this.baseUrl}/total/${ids}`);
  }

  create(internet: Internet) : Observable<Internet>{
    return this.http.post<Internet>(`${this.baseUrl}`, internet);
  }

  findById(id: string): Observable<Internet> {
    return this.http.get<Internet>(`${this.baseUrl}/${id}`);
  }

  update(internet: Internet) : Observable<Internet> {
    return this.http.put<Internet>(`${this.baseUrl}/${internet.id}`, internet);
  }

  delete(id: string): Observable<Internet> {
    return this.http.delete<Internet>(`${this.baseUrl}/${id}`);
  }
}
