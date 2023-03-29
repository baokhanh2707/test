import {Component, OnInit} from '@angular/core';
import {render} from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {

  constructor() {
    render(
      {
        id: '#myPaypalButtons',
        currency: 'AED',
        value: '100.00',
        onApprove: (details) => {

          alert('thành công');
        }
      }
    );
  }

  ngOnInit(): void {
  }

}
