import { CardComponent } from './components/card/card/card.component';
import { InternetUpdateComponent } from './components/internet/internet-update/internet-update.component';
import { InternetCreateComponent } from './components/internet/internet-create/internet-create.component';
import { InternetComponent } from './components/internet/internet/internet.component';
import { WaterDeleteComponent } from './components/water/water-delete/water-delete.component';
import { WaterUpdateComponent } from './components/water/water-update/water-update.component';
import { WaterCreateComponent } from './components/water/water-create/water-create.component';
import { AuthGuard } from './interceptors/auth.guard';
import { EnergyDeleteComponent } from './components/energy/energy-delete/energy-delete.component';
import { EnergyUpdateComponent } from './components/energy/energy-update/energy-update.component';
import { EnergyCreateComponent } from './components/energy/energy-create/energy-create.component';
import { EnergyComponent } from './components/energy/energy/energy.component';
import { WaterComponent } from './components/water/water/water.component';
import { HomeComponent } from './components/home/home/home.component';
import { NavComponent } from './components/nav/nav/nav.component';
import { RegisterComponent } from './components/register/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login/login.component';
import { AuthInterceptor } from './interceptors/auth.intercep';
import { InternetDeleteComponent } from './components/internet/internet-delete/internet-delete.component';

const routes: Routes = [
  {
    path: '',
    component: NavComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path:'energy',
        component: EnergyComponent
      },
      {
        path:'water',
        component: WaterComponent
      },
      {
        path:'water/create',
        component: WaterCreateComponent
      },
      {
        path: 'water/update/:id',
        component: WaterUpdateComponent
      },
      {
        path: 'water/delete/:id',
        component: WaterDeleteComponent
      },
      {
        path:'energy/create',
        component:EnergyCreateComponent
      },
      {
        path:'energy/update/:id',
        component: EnergyUpdateComponent
      },
      {
        path:'energy/delete/:id',
        component: EnergyDeleteComponent
      },
      {
        path:'internet',
        component: InternetComponent
      },
      {
        path: 'internet/create',
        component: InternetCreateComponent
      },

      {
        path: 'internet/update/:id',
        component: InternetUpdateComponent
      },
      {
        path: 'internet/delete/:id',
        component: InternetDeleteComponent
      },
      {
        path: 'card',
        component: CardComponent
      }
    ], canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path:'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
