import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Card } from 'src/app/components/models/card';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  baseUrl = 'http://localhost:8080/card'
  constructor(private http: HttpClient) { }

  findAll(): Observable<Card[]> {
    return this.http.get<Card[]>(this.baseUrl);
  }

  create(card: Card) : Observable<Card>{
    return this.http.post<Card>(`${this.baseUrl}`, card);
  }

  findById(id: string): Observable<Card> {
    console.log(id)
    return this.http.get<Card>(`${this.baseUrl}/${id}`);
  }

  update(card: Card) : Observable<Card> {
    return this.http.put<Card>(`${this.baseUrl}/${card.id}`, card);
  }

  delete(id: string): Observable<Card> {
    return this.http.delete<Card>(`${this.baseUrl}/${id}`);
  }
}
