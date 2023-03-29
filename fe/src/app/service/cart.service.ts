import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {CartDetail} from '../entity/cart-detail';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  itemCount: BehaviorSubject<any> = new BehaviorSubject<any>('');
  URL_CART = 'http://localhost:8080/api/public/carts';

  constructor(private httpClient: HttpClient) {
  }

  addToCart(cartDetail: CartDetail): Observable<any> {
    console.log(cartDetail);
    return this.httpClient.post(this.URL_CART, cartDetail);
  }

  getCartByIdCustomer(id: number): Observable<any> {
    return this.httpClient.get(this.URL_CART + '/' + id);
  }

  updateAmountByCart(cartDetail: CartDetail): Observable<any> {
    return this.httpClient.patch(this.URL_CART + '/updateQuantity', cartDetail);
  }

  getAmountExist(idCartDetail: number | undefined): Observable<any> {
    return this.httpClient.get(this.URL_CART + '/amount-exist/' + idCartDetail);
  }

  deleteCartDetail(idCartDetail: any): Observable<any> {
    return this.httpClient.delete<CartDetail>(this.URL_CART + '/delete/' + idCartDetail);
  }

  getCount(): Observable<any> {
    return this.itemCount.asObservable();
  }

  setCount(count: number): void {
    this.itemCount.next(count);
  }
}
