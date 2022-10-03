import { Expenses } from './../../models/other-Expenses';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OtherExpensesService } from 'src/app/services/other-Expenses/other-expenses.service';

@Component({
  selector: 'app-other-expenses-delete',
  templateUrl: './other-expenses-delete.component.html',
  styleUrls: ['./other-expenses-delete.component.css']
})
export class OtherExpensesDeleteComponent implements OnInit {
  expenses: Expenses = {
    description:'',
    price:0,
    buyDate:'',
  }

  constructor(private router: Router, private service: OtherExpensesService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') as any;
    this.service.findById(id).subscribe(expenses =>{
      this.expenses = expenses
    })
  }

  cancel(): void{
    this.router.navigate(['otherExpenses'])
  }

  delete(): void {
    const id = this.expenses.id as any;
    this.service.delete(id).subscribe(()=>{
      this.router.navigate(['otherExpenses'])
    })
  }
}
