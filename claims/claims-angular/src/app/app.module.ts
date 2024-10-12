import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {AppComponent} from "./app.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserModule} from "@angular/platform-browser";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {AccidentClaimComponent} from "./components/accident-claim/accident-claim.component";



const routes : Routes = [
  { path: '', component: HomeComponent },
  {path: 'login',component:LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'test', component:AccidentClaimComponent},

]
@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    AccidentClaimComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
