import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { WaterService } from 'src/app/services/water/water.service';
import { Water } from '../../models/water';

@Component({
  selector: 'app-water-update',
  templateUrl: './water-update.component.html',
  styleUrls: ['./water-update.component.css']
})
export class WaterUpdateComponent implements OnInit {

  water: Water = {
    description: '',
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

  update(): void {
    this.service.update(this.water).subscribe(() =>{
      this.router.navigate(['water'])
    })
  }

}
