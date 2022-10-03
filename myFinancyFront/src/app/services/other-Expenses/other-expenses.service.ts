import { Expenses } from './../../components/models/other-Expenses';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from '../login/login.service';

@Injectable({
  providedIn: 'root'
})
export class OtherExpensesService {
  baseUrl = 'http://localhost:8080/otherExpenses'
  constructor(private http: HttpClient, private userService: LoginService) { }

  findAll(token: string): Observable<Expenses[]> {
    let ids= null;
    ids =this.userService.findByIdUser(token)
    return this.http.get<Expenses[]>(`${this.baseUrl}/total/${ids}`);
  }

  create(expenses: Expenses) : Observable<Expenses>{
    return this.http.post<Expenses>(`${this.baseUrl}`, expenses);
  }

  findById(id: string): Observable<Expenses> {
    return this.http.get<Expenses>(`${this.baseUrl}/${id}`);
  }

  update(expenses: Expenses) : Observable<Expenses> {
    return this.http.put<Expenses>(`${this.baseUrl}/${expenses.id}`, expenses);
  }

  delete(id: string): Observable<Expenses> {
    return this.http.delete<Expenses>(`${this.baseUrl}/${id}`);
  }
}
