import { Energy } from './../../models/energy';
import { EnergyService } from 'src/app/services/energy/energy.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-energy-update',
  templateUrl: './energy-update.component.html',
  styleUrls: ['./energy-update.component.css']
})
export class EnergyUpdateComponent implements OnInit {

  energy: Energy = {
    description: '',
    price:0,
    expenditure:0,
    dueDate:'',
    users:''
  }
  constructor(private router: Router, private service: EnergyService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') as any;
    this.service.findById(id).subscribe(energy =>{
      this.energy = energy
    })
  }

  cancel(): void{
    this.router.navigate(['energy'])
  }

  update(): void {
    this.service.update(this.energy).subscribe(() =>{
      this.router.navigate(['energy'])
    })
  }

}
