import { HeaderService } from './../../../services/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private service: HeaderService) { }

  ngOnInit(): void {
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
