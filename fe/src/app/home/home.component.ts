import {Component, OnInit} from '@angular/core';
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

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  imageList: Image[] = [];
  statusList: Status[] = [];
  typeList: TypeProduct[] = [];
  yearList: YearProduct[] = [];
  size = 6;
  first: any;
  last: any;
  productPage: Home[] = [];
  typeProduct = '';
  statusProduct = '';
  yearProduct = '';


  constructor(private productService: ProductService, private toastrService: ToastrService, private titleService: Title) {
    this.titleService.setTitle('BK CAR SPORT');
  }

  ngOnInit(): void {
    this.getAllProduct(this.typeProduct, this.statusProduct, this.yearProduct, this.size, true);
    this.getStatusProduct();
    this.getYearProduct();
    this.getTypeProduct();
  }

  getAllProduct(typeProduct: string, statusProduct: string, yearProduct: string, size: number, flag: boolean): void {
    if (!flag) {
      this.size = 6;
    }
    this.typeProduct = typeProduct;
    this.statusProduct = statusProduct;
    this.yearProduct = yearProduct;
    this.productService.getAllProduct(typeProduct, statusProduct, yearProduct, size).subscribe(data => {
      if (data.content.length !== null) {
        this.productPage = data.content;
        this.first = data.first;
        this.last = data.last;
        this.size = data.size;
        if ((typeProduct !== '' || statusProduct !== '' || yearProduct !== '' ) &&  !flag) {
          this.toastrService.success('Tìm kiếm thành công.', 'Thông Báo');
        }
      }
    }, error1 => {
      if (error1.status === 404) {
        this.toastrService.error('Tìm kiếm không thành công', 'thông báo');
      }
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
      console.log(data);
    });
  }

  getTypeProduct(): void {
    this.productService.getAllTypeProduct().subscribe(data => {
      this.typeList = data;
      console.log(data);
    });
  }

  getImageProduct(): void {
    this.productService.getAllUrlProduct().subscribe(data => {
      this.imageList = data;
    });
  }

  scroll(): void {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
}
