import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {Product} from '../entity/product';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {DetailDto} from '../dto/detail-dto';
import {Image} from '../dto/image';
import {ProductInType} from '../dto/product-in-type';
import {TokenService} from '../service/token/token.service';
import {CartService} from '../service/cart.service';
import {CartDetail} from '../entity/cart-detail';
import {Cart} from '../dto/cart.dto';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  // tslint:disable-next-line:ban-types
  roles: String[] = [];
  productDetail: Product | undefined;
  productDto: DetailDto = {};
  imageList: Image [] = [];
  image: string | undefined;
  quantity = 1;
  productInType: ProductInType [] = [];
  cartDetail: CartDetail = {};
  cartList: Cart[] = [];
  checkLogin = false;
  idProduct: string | null | undefined;

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute, private tokenService: TokenService,
              private cartService: CartService, private toastrService: ToastrService, private router: Router) {
    this.activatedRoute.paramMap.subscribe(data => {
      this.idProduct = data.get('id');
      {
        this.productService.getAllProductById(Number(this.idProduct)).subscribe(data2 => {
          this.productDto = data2;
          console.log(this.productDto);
        });
      }
      this.productService.getAllProductByIdType(Number(this.idProduct)).subscribe(data3 => {
        this.productInType = data3;
      });
    });

    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      {
        this.productService.getAllImageById(Number(id)).subscribe(data1 => {
          this.imageList = data1;
          this.image = data1[0].url;
        });
      }
    });
  }

  addToCart(): void {
    if (this.tokenService.getToken()) {
      console.log(this.productDto);
      this.checkLogin = true;
      // @ts-ignore
      if (this.quantity >= this.productDto?.amountExist) {
        this.toastrService.error('Số lượng bạn nhập lớn hơn số lượng sản phẩm còn trong kho.');

      } else if (this.quantity < 0) {
        this.toastrService.error('Số lượng bạn nhập phải lớn hơn 0.') ;
      } else if (isNaN(this.quantity)) {
        this.toastrService.error('Bạn không được nhập chữ vào đây.');
      } else {

        this.cartDetail.quantity = this.quantity;
        this.cartDetail.idProduct = this.productDto?.idProduct;
        this.cartDetail.idCustomer = Number(this.tokenService.getIdCustomer());
        this.cartDetail.price = Number(this.productDto?.price);
        this.cartService.addToCart(this.cartDetail).subscribe(() => {
          this.cartService.getCartByIdCustomer(Number(this.tokenService.getIdCustomer())).subscribe(data => {
            this.cartList = data;
            this.cartService.setCount(this.cartList.length);
            console.log(this.cartService.setCount(this.cartList.length) + 'bbb');
          });
          this.toastrService.success('Thêm vào giỏ hàng thành công.', 'Thông báo.');
        });
      }
    } else {
      this.toastrService.warning('Bạn phải đăng nhập để tiếp tục.', 'Thông báo.');
      this.router.navigateByUrl('/login');
    }
  }

  decrease(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  increase(): void {
    if (this.quantity < 10) {
      this.quantity++;
    }
  }

  ngOnInit(): void {
    this.scroll();
    this.roles = this.tokenService.getRole();
    this.checkLogin = true;
  }

  scroll(): void {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
}
