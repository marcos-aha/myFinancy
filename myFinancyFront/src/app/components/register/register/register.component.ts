import { LoginService } from './../../../services/login/login.service';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/register';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  hide= true;
  register: User = {
    name: '',
    email:'',
    income:'',
    username: '',
    password:''
  }

  name: FormControl= new FormControl(null, Validators.minLength(3));
  email: FormControl= new FormControl(null, Validators.email);
  username: FormControl= new FormControl(null, Validators.minLength(3));
  password: FormControl= new FormControl(null, Validators.minLength(3));

  constructor(private router: Router, private service: LoginService) { }

  ngOnInit(): void {
  }

  validaCampos(): boolean{
    return this.username.valid && this.password.valid && this.email.valid && this.password.valid && this.name.valid;
  }

  create(): void {
    this.service.create(this.register).subscribe(() =>{
      this.router.navigate(['login'])
    })
  }

  cancel(): void{
    this.router.navigate(['login'])
  }

}
