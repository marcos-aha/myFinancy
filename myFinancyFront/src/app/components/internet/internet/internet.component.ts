import { HeaderService } from './../../../services/header/header.service';
import { Internet } from './../../models/internet';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { InternetService } from 'src/app/services/internet/internet.service';

@Component({
  selector: 'app-internet',
  templateUrl: './internet.component.html',
  styleUrls: ['./internet.component.css']
})
export class InternetComponent implements OnInit {
  internet: Internet[] = []; 
  displayedColumns: string[] = ['description', 'price','dueDate', 'actions'];
  dataSource = new MatTableDataSource<Internet>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private service: InternetService, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Internet',
      icon: 'language',
      routerUrl: '/internet'
    }
   }

  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
    this.service.findAll(token).subscribe(internet =>{
      this.internet = internet
      this.dataSource = new MatTableDataSource<Internet>(internet);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
