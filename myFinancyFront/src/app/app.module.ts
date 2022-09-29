
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//imports angular material
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { RegisterComponent } from './components/register/register/register.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { HomeComponent } from './components/home/home/home.component';
import { NavComponent } from './components/nav/nav/nav.component';
import { HeaderComponent } from './components/header/header/header.component';
import { EnergyComponent } from './components/energy/energy/energy.component';
import { WaterComponent } from './components/water/water/water.component';
import { HttpClientModule } from '@angular/common/http';
import { EnergyCreateComponent } from './components/energy/energy-create/energy-create.component';
import { EnergyUpdateComponent } from './components/energy/energy-update/energy-update.component';
import { EnergyDeleteComponent } from './components/energy/energy-delete/energy-delete.component';
import { AuthInterceptorProvider } from './interceptors/auth.intercep';
import { WaterCreateComponent } from './components/water/water-create/water-create.component';
import { WaterUpdateComponent } from './components/water/water-update/water-update.component';
import { WaterDeleteComponent } from './components/water/water-delete/water-delete.component';
import { InternetComponent } from './components/internet/internet/internet.component';
import { InternetCreateComponent } from './components/internet/internet-create/internet-create.component';
import { InternetUpdateComponent } from './components/internet/internet-update/internet-update.component';
import { InternetDeleteComponent } from './components/internet/internet-delete/internet-delete.component';
import { CardComponent } from './components/card/card/card.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CommonModule} from '@angular/common';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavComponent,
    HeaderComponent,
    EnergyComponent,
    WaterComponent,
    EnergyCreateComponent,
    EnergyUpdateComponent,
    EnergyDeleteComponent,
    WaterCreateComponent,
    WaterUpdateComponent,
    WaterDeleteComponent,
    InternetComponent,
    InternetCreateComponent,
    InternetUpdateComponent,
    InternetDeleteComponent,
    CardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatSidenavModule,
    MatSelectModule,
    MatTableModule,
    MatRadioModule,
    MatListModule,
    MatCardModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    CommonModule
  ],
  providers: [AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
