import { LoginService } from 'src/app/services/login/login.service';
import { Internet } from './../../models/internet';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InternetService } from 'src/app/services/internet/internet.service';

@Component({
  selector: 'app-internet-create',
  templateUrl: './internet-create.component.html',
  styleUrls: ['./internet-create.component.css']
})
export class InternetCreateComponent implements OnInit {

  internet : Internet = {
    description: 'Conta de internet',
    price: 0,
    dueDate: '',
    users: String(this.idUser()) 
  }


  constructor(private router: Router, private service: InternetService, private userService: LoginService) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.router.navigate(['internet'])
  }

  create() : void {
    this.service.create(this.internet).subscribe(() => {
      this.router.navigate(['home']);
    })
  }

  idUser(){
    let token = localStorage.getItem('token') as any;
    return this.userService.findByIdUser(token)
  }

}
