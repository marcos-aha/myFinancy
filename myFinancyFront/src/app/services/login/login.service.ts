import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Route, Router } from '@angular/router';
import { User } from 'src/app/components/models/register';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseUrl = 'http://localhost:8080/user';
  constructor(private router: Router, private http: HttpClient) { }
  ids: string = '';

  create(register: User): Observable<User>{
    return this.http.post<User>(this.baseUrl, register);
  }

  findByIdUser(token: string): string{
     this.http.get(`${this.baseUrl}/id/${token}`, {
      observe: 'response',
      responseType: 'text'
    }).subscribe(resposta => {
      this.ids= resposta.body as any;
      return this.ids;
    })
    return this.ids;
  }
}
