import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CartDetail} from '../../dto/cart-detail';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  URL_CART = 'http://localhost:8080/api/user/carts';
  constructor(private httpClient: HttpClient) { }
addToCart(cartDetail: CartDetail): Observable<any> {
  return this.httpClient.post(this.URL_CART, cartDetail);
}

getCartByIdCustomer(id: number): Observable<any> {
  return this.httpClient.get(this.URL_CART + '/card/' + id);
}

updateAmountByCart(cartDetail: CartDetail): Observable<any> {
  return this.httpClient.patch(this.URL_CART + '/updateQuantity', cartDetail);
}
}
