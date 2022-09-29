import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Login } from '../components/models/login';



@Injectable({
  providedIn: 'root'
})
export class AuthService {
    baseUrl = 'http://localhost:8080/user'
  jwtService: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }

  authenticate(login: Login) {
    return this.http.post(`${this.baseUrl}/login`, login, {
      observe: 'response',
      responseType: 'text'
    })
  }

  successfulLogin(authToken: string) {
    localStorage.setItem('token', authToken);
  }

  isAuthenticate(){
    let token = localStorage.getItem('token')
    if( token != null) {
      return !this.jwtService.isTokenExpired(token)
    }
    return false;
  }

  logout() {
    localStorage.clear()
  }
}