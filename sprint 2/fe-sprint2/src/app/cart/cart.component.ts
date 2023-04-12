import { Component, OnInit } from '@angular/core';
import {CartService} from '../service/cart/cart.service';
import {TokenService} from '../service/token/token.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
idCustomer = 0;
  constructor(private cartService: CartService, private tokenService: TokenService , private toastrService: ToastrService) { }

  ngOnInit(): void {
  }
getCartList(): void{
    // this.idCustomer= Number(this.tokenService.)
}
}
