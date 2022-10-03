import { Expenses } from './../../models/other-Expenses';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OtherExpensesService } from 'src/app/services/other-Expenses/other-expenses.service';

@Component({
  selector: 'app-other-expenses-update',
  templateUrl: './other-expenses-update.component.html',
  styleUrls: ['./other-expenses-update.component.css']
})
export class OtherExpensesUpdateComponent implements OnInit {
  expenses: Expenses = {
    description: '',
    price:0,
    buyDate:'',
    users:''
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

  update(): void {
    this.service.update(this.expenses).subscribe(() =>{
      this.router.navigate(['otherExpenses'])
    })
  }

}
