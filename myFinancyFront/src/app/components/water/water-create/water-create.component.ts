import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';
import { WaterService } from 'src/app/services/water/water.service';
import { Water } from '../../models/water';

@Component({
  selector: 'app-water-create',
  templateUrl: './water-create.component.html',
  styleUrls: ['./water-create.component.css']
})
export class WaterCreateComponent implements OnInit {

  water : Water = {
    description: 'Conta de água',
    price: 0,
    expenditure: 0,
    dueDate: '',
    users: String(this.idUser()) 
  }


  constructor(private router: Router, private service: WaterService,private userService: LoginService) { }

  ngOnInit(): void {
  }

  cancel(): void {
    this.router.navigate(['water'])
  }

  create() : void {
    this.service.create(this.water).subscribe(() => {
      this.router.navigate(['home']);
    })
  }

  idUser(){
    let token = localStorage.getItem('token') as any;
    return this.userService.findByIdUser(token)
  }

}
