import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {AppComponent} from "./app.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserModule} from "@angular/platform-browser";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";



const routes : Routes = [
  {path: 'login',component:LoginComponent},
  {path: 'register', component:RegisterComponent},

]
@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent
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
