import { LoginService } from './../../../services/login/login.service';
import { HeaderService } from './../../../services/header/header.service';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Energy } from '../../models/energy';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Water } from '../../models/water';
import { WaterService } from 'src/app/services/water/water.service';

@Component({
  selector: 'app-water',
  templateUrl: './water.component.html',
  styleUrls: ['./water.component.css']
})
export class WaterComponent implements OnInit {
  water: Water[] = []; 
  displayedColumns: string[] = ['description', 'price', 'expenditure', 'dueDate', 'actions'];
  dataSource = new MatTableDataSource<Energy>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private service: WaterService, private headerService: HeaderService) { 
    headerService.headerData = {
      title: 'Água',
      icon: 'water_drop',
      routerUrl: '/water'
    }
  }

  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
    this.service.findAll(token).subscribe(water =>{
      this.water = water
      this.dataSource = new MatTableDataSource<Energy>(water);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

 


}
