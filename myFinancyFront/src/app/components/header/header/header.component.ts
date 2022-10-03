import { LoginService } from './../../../services/login/login.service';
import { HeaderService } from './../../../services/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  name: String = ''

  constructor(private service: HeaderService, private userService: LoginService) { }

  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
    this.userService.findByIdUser(token);
    this.userService.findByUser().subscribe(user =>
      this.name= user.name
    )

  }

  get title() : string {
    return this.service.headerData.title
  }

  get icon() : string {
    return this.service.headerData.icon
  }

  get routerUrl() : string {
    return this.service.headerData.routerUrl
  }

}
