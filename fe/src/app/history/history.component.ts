import {Component, OnInit} from '@angular/core';
import {TokenService} from '../service/token/token.service';
import {CartService} from '../service/cart.service';
import {OderDto} from '../dto/oder-dto';
import {OderDetailDto} from '../dto/oder-detail-dto';
import {OderJson} from '../dto/oder-json';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  idCustomer = 0;
  page = 0;
  historyPayList: OderDto[] = [];
  historyPayJson!: OderJson;
  detailPayList: OderDetailDto[] = [];

  constructor(private tokenService: TokenService,
              private cartService: CartService,
              private title: Title) {this.title.setTitle('Lịch sử đặt hàng');
  }

  ngOnInit(): void {
    this.getAllHistoryPay();
  }

  getAllHistoryPay(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.historyPay(this.idCustomer, this.page).subscribe(data => {
      this.historyPayList = data.content;
      this.historyPayJson = data;
    });
  }

  getAllDetailPay(id: number | undefined): void {
    this.cartService.getHistoryPayDetail(id).subscribe(data => {
      this.detailPayList = data;
    });
  }

  gotoPage(pageNumber: number): void {
    this.page = pageNumber;
    this.ngOnInit();
  }
}
