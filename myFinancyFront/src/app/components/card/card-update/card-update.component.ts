import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CardService } from 'src/app/services/card/card.service';
import { Card } from '../../models/card';

@Component({
  selector: 'app-card-update',
  templateUrl: './card-update.component.html',
  styleUrls: ['./card-update.component.css']
})
export class CardUpdateComponent implements OnInit {
  card: Card = {
    description: '',
    price:0,
    closingDate:'',
    dueDate:'',
    users:''
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

  update(): void {
    this.service.update(this.card).subscribe(() =>{
      this.router.navigate(['card'])
    })
  }

}
