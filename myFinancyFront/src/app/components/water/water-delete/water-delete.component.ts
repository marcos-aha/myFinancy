import { Water } from './../../models/water';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { WaterService } from 'src/app/services/water/water.service';

@Component({
  selector: 'app-water-delete',
  templateUrl: './water-delete.component.html',
  styleUrls: ['./water-delete.component.css']
})
export class WaterDeleteComponent implements OnInit {
  water: Water = {
    description:'',
    price:0,
    expenditure:0,
    dueDate:'',
    users: ''
  }

  constructor(private router: Router, private service: WaterService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id') as any;
    this.service.findById(id).subscribe(water =>{
      this.water = water
    })
  }

  cancel(): void{
    this.router.navigate(['water'])
  }

  delete(): void {
    const id = this.water.id as any;
    this.service.delete(id).subscribe(()=>{
      this.router.navigate(['water'])
    })
  }

}
