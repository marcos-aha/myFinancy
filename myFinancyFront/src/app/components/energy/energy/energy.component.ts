import { HeaderService } from './../../../services/header/header.service';
import { LoginService } from './../../../services/login/login.service';
import { Energy } from './../../models/energy';
import { EnergyService } from './../../../services/energy/energy.service';

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { delay } from 'rxjs';



@Component({
  selector: 'app-energy',
  templateUrl: './energy.component.html',
  styleUrls: ['./energy.component.css']
})
export class EnergyComponent implements OnInit {
  energy: Energy[] = []; 
  displayedColumns: string[] = ['description', 'price', 'expenditure', 'dueDate', 'actions'];
  dataSource = new MatTableDataSource<Energy>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;


  constructor(private service: EnergyService, private userService: LoginService, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Energia',
      icon: 'electric_bolt',
      routerUrl: '/energy'
    }
   }


  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
      this.service.findAll(token).subscribe(energy =>{
        this.energy = energy
        this.dataSource = new MatTableDataSource<Energy>(energy);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
