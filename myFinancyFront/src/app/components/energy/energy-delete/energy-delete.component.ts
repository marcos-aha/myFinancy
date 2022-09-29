import { Energy } from './../../models/energy';
import { EnergyService } from 'src/app/services/energy/energy.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-energy-delete',
  templateUrl: './energy-delete.component.html',
  styleUrls: ['./energy-delete.component.css']
})
export class EnergyDeleteComponent implements OnInit {
  energy: Energy = {
    description:'',
    price:0,
    expenditure:0,
    dueDate:'',
  }

  constructor(private router: Router, private service: EnergyService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') as any;
    console.log(id)
    this.service.findById(id).subscribe(energy =>{
      this.energy = energy
    })
  }

  cancel(): void{
    this.router.navigate(['energy'])
  }

  delete(): void {
    const id = this.energy.id as any;
    this.service.delete(id).subscribe(()=>{
      this.router.navigate(['energy'])
    })
  }

}
