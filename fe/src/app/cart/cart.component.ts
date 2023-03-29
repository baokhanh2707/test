import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CartService} from '../service/cart.service';
import {TokenService} from '../service/token/token.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';
import {Cart} from '../dto/cart.dto';
import {CartDetail} from '../entity/cart-detail';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartList: Cart[] = [];
  idCustomer = 0;
  cartDetail: CartDetail = {};
  amountExist = 0;
  totalMany = 0;


  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private toastrService: ToastrService,
              private title: Title) {
  }

  ngOnInit(): void {
    this.getCartList();
  }

  getCartList(): void {
    this.idCustomer = Number(this.tokenService.getIdCustomer());
    this.cartService.getCartByIdCustomer(this.idCustomer).subscribe(data => {
      this.cartList = data[0];
      this.totalMany = data[1];
      this.cartService.setCount(this.cartList.length);
    });
  }

  decrease(idCartDetail: number | undefined): void {
    for (const element of this.cartList) {
      if (element.idCardDetail === idCartDetail) {
        // @ts-ignore
        if (element.quantity <= 1) {
          element.quantity = 1;
        } else {
          // @ts-ignore
          element.quantity -= 1;
        }
        this.cartDetail.quantity = element.quantity;
        this.cartDetail.idCartDetail = idCartDetail;
        this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
          this.getCartList();
        });
      }
    }
  }

  increase(idCartDetail: number | undefined): void {
    console.log(this.cartList);
    for (const element of this.cartList) {
      if (element.idCardDetail === idCartDetail) {
        // @ts-ignore
        if (element.quantity >= element.amountExist) {
          element.quantity = element.amountExist;
          this.toastrService.warning('Số lượng bạn chọn đã bằng số lượng sản phẩm còn trong kho.', 'Thông báo');
        } else {
          // @ts-ignore
          element.quantity += 1;
        }
        this.cartDetail.quantity = element.quantity;
        this.cartDetail.idCartDetail = idCartDetail;
        this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
          this.getCartList();
        });
      }
    }
  }

  private updateAmount(quantity: number | undefined, idCartDetail: number | undefined): void {
    this.cartDetail.quantity = quantity;
    this.cartDetail.idCartDetail = idCartDetail;
    this.cartService.updateAmountByCart(this.cartDetail).subscribe(() => {
      this.ngOnInit();
    });
  }

  change(quantity: number, idCartDetail: number | undefined): void {
    this.getAmountExistProduct(idCartDetail);
    if (isNaN(quantity)) {
      this.toastrService.warning('Bạn không được nhập chữ vào đây.', 'Thông báo');
      this.getCartList();
    } else if (quantity >= this.amountExist) {
      this.toastrService.warning('Số lượng bạn nhập lớn hơn số lượng sản phẩm còn trong kho.', 'Thông báo');
      this.getCartList();
    } else if (quantity < 0) {
      this.toastrService.warning('Số lượng bạn nhập phải lớn hơn 0.', 'Thông báo');
      this.getCartList();
    } else {
      this.cartDetail.quantity = quantity;
      this.cartDetail.idCartDetail = idCartDetail;
      this.updateAmount(quantity, idCartDetail);
    }
  }

  private getAmountExistProduct(idCartDetail: number | undefined): void {
    this.cartService.getAmountExist(idCartDetail).subscribe(data => {
      this.amountExist = data.amountExist;
    });
  }

  deleteByIdCartDetail(): void {
    this.cartService.deleteCartDetail(this.cartDetail.idCartDetail).subscribe(() => {
      this.toastrService.success('Xóa thành công', 'Thông báo');
      this.getCartList();
    }, error => {
      this.toastrService.error('Xóa không thành công', 'Thông báo');
    });
  }
}
