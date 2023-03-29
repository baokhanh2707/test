import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {CartComponent} from './cart/cart.component';
import {HomeComponent} from './home/home.component';
import {DetailComponent} from './detail/detail.component';
import {PaymentsComponent} from './payments/payments.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'cart', component: CartComponent
  },
  {
    path: '', component: HomeComponent
  },
  {
    path: 'detail/:id', component: DetailComponent
  },
  {
    path: 'payment' , component: PaymentsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
