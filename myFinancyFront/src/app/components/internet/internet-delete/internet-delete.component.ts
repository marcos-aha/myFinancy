import { InternetComponent } from './../internet/internet.component';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InternetService } from 'src/app/services/internet/internet.service';
import { Internet } from '../../models/internet';

@Component({
  selector: 'app-internet-delete',
  templateUrl: './internet-delete.component.html',
  styleUrls: ['./internet-delete.component.css']
})
export class InternetDeleteComponent implements OnInit {

  internet: Internet = {
    description:'',
    price:0,
    dueDate:'',
  }

  constructor(private router: Router, private service: InternetService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id') as any;
    this.service.findById(id).subscribe(internet =>{
      this.internet = internet
    })
  }

  cancel(): void{
    this.router.navigate(['internet'])
  }

  delete(): void {
    let id = this.internet.id as any;
    this.service.delete(id).subscribe(()=>{
      this.router.navigate(['internet'])
    })
  }

}
