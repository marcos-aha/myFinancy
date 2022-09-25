import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from '../../models/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: Login = {
    username: '',
    password: ''
  }

  username: FormControl = new FormControl(null,Validators.minLength(3));
  password: FormControl = new FormControl(null,Validators.minLength(3));
  hide = true;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  register(): void{
    this.router.navigate(['/register'])
  }

  validaCampos(): boolean{
    return this.username.valid && this.password.valid;
  }

}
