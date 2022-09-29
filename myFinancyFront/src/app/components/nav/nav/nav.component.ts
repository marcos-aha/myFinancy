import { HeaderService } from './../../../services/header/header.service';
import { AuthService } from './../../../services/auth.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private router: Router, private auth: AuthService) {

   }

  ngOnInit(): void {
    this.router.navigate(['home'])
  }

  logout()  {
    this.auth.logout();
    this.router.navigate(['login'])
  }


}
