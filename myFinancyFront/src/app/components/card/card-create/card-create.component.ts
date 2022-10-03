import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';
import { CardService } from './../../../services/card/card.service';
import { Component, OnInit } from '@angular/core';
import { Card } from '../../models/card';

@Component({
  selector: 'app-card-create',
  templateUrl: './card-create.component.html',
  styleUrls: ['./card-create.component.css']
})
export class CardCreateComponent implements OnInit {

  card : Card = {
    description: '',
    price: 0,
    closingDate: '',
    dueDate: '',
    users: String(this.idUser())
  }

  constructor(private service: CardService, private router: Router, private userService: LoginService) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.router.navigate(['card'])
  }

  create() : void {
    this.idUser()
    this.service.create(this.card).subscribe(() => {
      this.router.navigate(['card']);
    })
  }

  idUser(){
    let token = localStorage.getItem('token') as any;
    return this.userService.findByIdUser(token)
  }

}
