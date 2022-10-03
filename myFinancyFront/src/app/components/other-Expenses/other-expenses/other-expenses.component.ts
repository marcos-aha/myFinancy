import { OtherExpensesService } from './../../../services/other-Expenses/other-expenses.service';
import { Expenses } from './../../models/other-Expenses';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { LoginService } from 'src/app/services/login/login.service';
import { HeaderService } from 'src/app/services/header/header.service';

@Component({
  selector: 'app-other-expenses',
  templateUrl: './other-expenses.component.html',
  styleUrls: ['./other-expenses.component.css']
})
export class OtherExpensesComponent implements OnInit {
  expenses: Expenses[] = []; 
  displayedColumns: string[] = ['description', 'price', 'buyDate', 'actions'];
  dataSource = new MatTableDataSource<Expenses>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;


  constructor(private service: OtherExpensesService, private userService: LoginService, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Outras despesas',
      icon: 'shopping_cart',
      routerUrl: '/OtherExpenses'
    }
   }


  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
      this.service.findAll(token).subscribe(expenses =>{
        this.expenses = expenses
        this.dataSource = new MatTableDataSource<Expenses>(expenses);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
