import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CardService } from 'src/app/services/card/card.service';
import { Card } from '../../models/card';

@Component({
  selector: 'app-card-delete',
  templateUrl: './card-delete.component.html',
  styleUrls: ['./card-delete.component.css']
})
export class CardDeleteComponent implements OnInit {
  card: Card = {
    description:'',
    price:0,
    closingDate:'',
    dueDate:'',
  }

  constructor(private router: Router, private service: CardService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') as any;
    this.service.findById(id).subscribe(card =>{
      this.card = card
    })
  }

  cancel(): void{
    this.router.navigate(['card'])
  }

  delete(): void {
    const id = this.card.id as any;
    this.service.delete(id).subscribe(()=>{
      this.router.navigate(['card'])
    })
  }

}
