import { HeaderService } from './../../services/header/header.service';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';
import { Perfil } from '../models/perfil';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  perfil: Perfil = {
    name: '',
    email: '', 
    income:0,
    password: ''
  }
  newPassword ='';
  hide= true;
  constructor(private router: Router, private service: LoginService, private route: ActivatedRoute, 
    private header: HeaderService) {
      header.headerData = {
        title: "Perfil",
        icon: "account_circle",
        routerUrl: "/perfil"
      }
     }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') as any;
    this.service.findByUser().subscribe(perfil =>{
      this.perfil = perfil
    })
  }

  cancel(): void{
    this.router.navigate(['home'])
  }

  update(): void {
    this.validPassword()
    this.service.update(this.perfil).subscribe(() =>{
      this.router.navigate(['home'])
    })
  }

  validPassword() {
    if(this.newPassword !== '') {
      this.perfil.password = this.newPassword
    }
  }

}
