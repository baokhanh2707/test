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
import {TokenService} from '../service/token/token.service';
import {DetailDto} from '../dto/detail-dto';
import {CartDetail} from '../entity/cart-detail';
import {CartService} from '../service/cart.service';
import {Cart} from '../dto/cart.dto';
import {Route, Router} from '@angular/router';

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
  @ViewChild('typeProductSearch') inputType: any;
  @ViewChild('statusProductSearch') inputStatus: any;
  @ViewChild('yearProductSearch') inputYear: any;
  typeProduct = '';
  statusProduct = '';
  yearProduct = '';
  oneProductHome: ProductOne = {};
  checkLogin = false;
  quantity = 1;
  cartDetail: CartDetail = {};
  cartList: Cart[] = [];
  // tslint:disable-next-line:ban-types
  roles: String[] = [];

  constructor(private productService: ProductService, private toastrService: ToastrService, private titleService: Title,
              private tokenService: TokenService,
              private cartService: CartService,
              private router: Router
  ) {
    this.titleService.setTitle('BK CAR SPORT');
  }

  ngOnInit(): void {
    this.getAllProduct(this.typeProduct, this.statusProduct, this.yearProduct, this.size, true);
    this.getStatusProduct();
    this.getYearProduct();
    this.getTypeProduct();
    this.getProductOneHome();
    this.roles = this.tokenService.getRole();
    this.checkLogin = true;
  }

  addToCart(idProduct: number | undefined, price: number | undefined): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.cartDetail.quantity = this.quantity;
      this.cartDetail.price = price;
      this.cartDetail.idProduct = idProduct;
      this.cartDetail.idCustomer = Number(this.tokenService.getIdCustomer());
      this.cartService.addToCart(this.cartDetail).subscribe(() => {
        this.cartService.getCartByIdCustomer(Number(this.tokenService.getIdCustomer())).subscribe(data => {
          this.cartList = data;
          this.cartService.setCount(this.cartList.length);
        });
        this.toastrService.success('Thêm vào giỏ hàng thành công.', 'Thông báo.');
        this.router.navigateByUrl('/cart');

      });

    } else {
      this.toastrService.warning('Bạn phải đăng nhập để tiếp tục.', 'Thông báo.');
      this.router.navigateByUrl('/login');
    }
  }

  getProduct(size: number): void {
    this.productService.getProduct(size).subscribe(data => {
      this.productPage = data.content;
      this.first = data.first;
      this.last = data.last;
      this.size = data.size;
    });
  }


  // tslint:disable-next-line:typedef
  isAdmin() {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.roles.length; i++) {
      if (this.roles[i] === 'ADMIN') {
        return true;
      }
    }
    return false;
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
