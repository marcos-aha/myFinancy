import { HeaderService } from './../../../services/header/header.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CardService } from 'src/app/services/card/card.service';
import { Card } from '../../models/card';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  card: Card[] = []; 
  displayedColumns: string[] = ['description', 'price', 'dueDate','closingDate', 'actions'];
  dataSource = new MatTableDataSource<Card>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private service: CardService, private headerService: HeaderService) { 
    headerService.headerData = {
      title: 'Cartão',
      icon: 'credit_card',
      routerUrl: '/card'
    }
  }

  ngOnInit(): void {
    let token = localStorage.getItem('token') as any;
    this.service.findAll(token).subscribe(card =>{
      this.card = card
      this.dataSource = new MatTableDataSource<Card>(card);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
