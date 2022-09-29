import { LoginService } from './../../../services/login/login.service';
import { FormControl, Validators } from '@angular/forms';
import { Energy } from './../../models/energy';
import { EnergyService } from './../../../services/energy/energy.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConstantPool } from '@angular/compiler';

@Component({
  selector: 'app-energy-create',
  templateUrl: './energy-create.component.html',
  styleUrls: ['./energy-create.component.css']
})
export class EnergyCreateComponent implements OnInit {

  energy : Energy = {
    description: 'Conta de energia',
    price: 0,
    expenditure: 0,
    dueDate: '',
    users: String(this.idUser())
  }


  constructor(private router: Router, private service: EnergyService, private userService: LoginService) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.router.navigate(['energy'])
  }

  create() : void {
    this.idUser()
    this.service.create(this.energy).subscribe(() => {
      this.router.navigate(['home']);
    })
  }

  idUser(){
    let token = localStorage.getItem('token') as any;
    return this.userService.findByIdUser(token)
  }

}
