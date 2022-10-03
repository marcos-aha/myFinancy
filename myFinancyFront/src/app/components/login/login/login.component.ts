import { AuthService } from './../../../services/auth.service';
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

  constructor(private router: Router, private auth: AuthService ) { }

  ngOnInit(): void {
  }

  register(){
    this.auth.authenticate(this.login).subscribe(resposta =>{
      let token = resposta.body as any;
      this.auth.successfulLogin(token.substring(7));
    })
    this.router.navigate(['/register'])
  }

  validaCampos(): boolean{
    return this.username.valid && this.password.valid;
  }

  validUser(){
    this.auth.authenticate(this.login).subscribe(resposta=>{
      const token = resposta.body as any;
      this.auth.successfulLogin(token.substring(7));
      this.router.navigate(['home'])
    }), ()=> {
      
    }
  }

}
