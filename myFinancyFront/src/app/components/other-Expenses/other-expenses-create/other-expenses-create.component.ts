import { Expenses } from './../../models/other-Expenses';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OtherExpensesService } from 'src/app/services/other-Expenses/other-expenses.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-other-expenses-create',
  templateUrl: './other-expenses-create.component.html',
  styleUrls: ['./other-expenses-create.component.css']
})
export class OtherExpensesCreateComponent implements OnInit {
  expenses : Expenses = {
    description: '',
    price: 0,
    buyDate: '',
    users: String(this.idUser())
  }
  constructor(private router: Router, private service: OtherExpensesService, private userService: LoginService) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.router.navigate(['otherExpenses'])
  }

  create() : void {
    this.idUser()
    this.service.create(this.expenses).subscribe(() => {
      this.router.navigate(['otherExpenses']);
    })
  }

  idUser(){
    let token = localStorage.getItem('token') as any;
    return this.userService.findByIdUser(token)
  }

}
