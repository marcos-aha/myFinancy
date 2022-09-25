import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Register } from '../../models/register';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  hide= true;
  register: Register = {
    name: '',
    email:'',
    income:'',
    username: '',
    password:''
  }

  name: FormControl= new FormControl(null, Validators.minLength(3));
  email: FormControl= new FormControl(null, Validators.email);
  income: FormControl= new FormControl(null, Validators.nullValidator);
  username: FormControl= new FormControl(null, Validators.minLength(3));
  password: FormControl= new FormControl(null, Validators.minLength(3));

  constructor() { }

  ngOnInit(): void {
  }

  validaCampos(): boolean{
    return this.username.valid && this.password.valid && this.email.valid && this.income.valid && this.password.valid && this.name.valid;
  }

}
