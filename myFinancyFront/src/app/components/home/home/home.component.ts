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

  

}
