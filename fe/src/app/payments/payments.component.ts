import {Component, OnInit} from '@angular/core';
import {render} from 'creditcardpayments/creditCardPayments';
import {CartService} from '../service/cart.service';
import {TokenService} from '../service/token/token.service';
import {CustomerService} from '../service/customer.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Payment} from '../entity/payment';
import {Cart} from '../dto/cart.dto';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {
  idCustomer = 0;
  formPay: FormGroup = new FormGroup({});
  payDto: Payment = {};
  sumMoneyAll = 0;
  cartList: Cart[] = [];
  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private customerService: CustomerService,
  ) {
  }

  ngOnInit(): void {
    this.formPay = new FormGroup({
      nameCustomer: new FormControl('', Validators.required),
      phoneNumber: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      note: new FormControl('')
    });
    this.getCartList();
    this.getCustomerById();
  }

  getCustomerById(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.customerService.getCustomer(this.idCustomer).subscribe(data => {
      this.formPay.patchValue(data[0]);
    });
  }

  pay(): void {
    this.payDto = this.formPay.value;
    // @ts-ignore
    this.payDto.idCustomer = +this.tokenService.getIdCustomer();
    this.payDto.total = this.sumMoneyAll;
    this.cartService.paymentCart(this.payDto).subscribe(() => {
      this.ngOnInit();
    });
  }
  getCartList(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.getCartByIdCustomer(this.idCustomer).subscribe(data => {
      this.cartList = data[0];
      console.log(data);
      this.sumMoneyAll = data[1];
      render({
        id: '#myPaypalButton',
        currency: 'USD',
        value: (this.sumMoneyAll / 100000).toFixed(2),
        onApprove: (details) => {
          this.pay();
          location.href = 'http://localhost:4200/success';
        }
      });
    });
  }
}
