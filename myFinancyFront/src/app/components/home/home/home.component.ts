import { EnergyComponent } from './../../energy/energy/energy.component';
import { LoginService } from './../../../services/login/login.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { HeaderService } from 'src/app/services/header/header.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  expenses: Number=0;
  income: Number=0;
  

  constructor(private router: Router, private userService: LoginService,private headerService: HeaderService ) { 
    headerService.headerData = {
      title: 'Início',
      icon: 'home',
      routerUrl: '/home'
    }
  }

  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
    this.userService.findByIdUser(token)
    this.listExpenses()
    this.findIncome()
  }

  energy(): void {
    this.router.navigate(['energy'])
  
  }

  water(): void {
    this.router.navigate(['water'])
  }

  internet(): void {
    this.router.navigate(['internet'])
  }

  card(): void {
    this.router.navigate(['card'])
  }

  other(): void {
    this.router.navigate(['otherExpenses'])
  }

  listExpenses(){
    this.userService.findListAll().subscribe(list =>{
      this.expenses= list.reduce((acum, price) => Number(acum) + Number(price)) as any
    })
  }

  findIncome(){
    this.userService.findByUser().subscribe(user =>{
      this.income=user.income
    })
  }

  

}
