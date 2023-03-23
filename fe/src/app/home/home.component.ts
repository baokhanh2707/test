import {Component, OnInit, ViewChild} from '@angular/core';
import {Product} from '../entity/product';
import {Image} from '../entity/image';
import {ProductService} from '../service/product.service';
import {ToastrService} from 'ngx-toastr';
import {FormGroup} from '@angular/forms';
import {Home} from '../dto/home';
import {TypeProduct} from '../entity/type-product';
import {Status} from '../entity/status';
import {YearProduct} from '../entity/year-product';
import {error} from '@angular/compiler/src/util';
import {Title} from '@angular/platform-browser';
import {ProductOne} from '../dto/product-one';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  statusList: Status[] = [];
  typeList: TypeProduct[] = [];
  yearList: YearProduct[] = [];
  size = 6;
  first: any;
  last: any;
  productPage: Home[] = [];
  productList: Home[] = [];
  @ViewChild('typeProductSearch') inputType: any;
  @ViewChild('statusProductSearch') inputStatus: any;
  @ViewChild('yearProductSearch') inputYear: any;
  typeProduct = '';
  statusProduct = '';
  yearProduct = '';
  oneProductHome: ProductOne = {};


  constructor(private productService: ProductService, private toastrService: ToastrService, private titleService: Title) {
    this.titleService.setTitle('BK CAR SPORT');
  }

  ngOnInit(): void {
    this.getAllProduct(this.typeProduct, this.statusProduct, this.yearProduct, this.size, true);
    this.getStatusProduct();
    this.getYearProduct();
    this.getTypeProduct();
    this.getProductOneHome();
  }
  getProduct(size: number): void {
    this.productService.getProduct(size).subscribe(data => {
      this.productPage = data.content;
      this.first = data.first;
      console.log(data.last);
      this.last = data.last;
      this.size = data.size;
    });
  }

  getAllProduct(typeProduct: string, statusProduct: string, yearProduct: string, size: number, flag: boolean): void {
    if (!flag) {
      this.size = 6;
    }
    this.typeProduct = '';
    this.statusProduct = '';
    this.yearProduct = '';
    this.productService.getAllProduct(typeProduct, statusProduct, yearProduct, size).subscribe(data => {
      if (data.content.length !== null) {
        this.productPage = data.content;
        this.first = data.first;
        this.last = data.last;
        console.log(this.productPage);
        this.size = data.size;
        if ((typeProduct !== '' || statusProduct !== '' || yearProduct !== '') && !flag) {
          this.toastrService.success('Tìm kiếm thành công.', 'Thông Báo');
        }
      }
    }, error1 => {
      if (error1.status === 404) {
        this.toastrService.error('Tìm kiếm không thành công', 'thông báo');
      }
    });
  }

  getProductOneHome(): void {
    this.productService.getProductOneHome().subscribe(data => {
      this.oneProductHome = data;
    });
  }

  getStatusProduct(): void {
    this.productService.getAllStatusProduct().subscribe(data => {
      this.statusList = data;
    });
  }

  getYearProduct(): void {
    this.productService.getYearProduct().subscribe(data => {
      this.yearList = data;
    });
  }

  getTypeProduct(): void {
    this.productService.getAllTypeProduct().subscribe(data => {
      this.typeList = data;
    });
  }
  scroll(): void {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }

  resetSearch(): void {
    this.inputStatus.nativeElement.value = '';
    this.inputType.nativeElement.value = '';
    this.inputYear.nativeElement.value = '';
    this.getProduct(this.size);
  }
}
