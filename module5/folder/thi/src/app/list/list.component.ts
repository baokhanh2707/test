import {Component, OnInit} from '@angular/core';
import {SanPham} from '../san-pham';
import {LoHang} from '../lo-hang';
import {ThiService} from './thi.service';
import {LoHangJson} from './lo-hang-json';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  loHangs: LoHang[] = [];
  loHangPage!: LoHangJson;
  sanPhams: SanPham[] = [];
  day1 = '';
  day2 = '';
  idSanPham = 0;
  page = 0;
  temp: any;

  constructor(private thiService: ThiService) {
    this.thiService.getSanPham().subscribe(data => {
      this.sanPhams = data;
    });
  }

  ngOnInit(): void {
    this.getPageAndSearch(this.day1, this.day2, this.idSanPham.toString());
  }

  getPageAndSearch(day1: string, day2: string, idSanPham: string): void {
    this.day1 = day1;
    this.day2 = day2;
    this.idSanPham = +idSanPham;
    this.thiService.pageLoHang(day1.trim(), day2.trim(), +idSanPham, this.page).subscribe(data => {
        this.loHangPage = data;
        console.log(data);
      },
      error => {

      });
  }

  gotoPage(pageNumber: number): void {
    this.page = pageNumber;
    this.ngOnInit();
  }
}
