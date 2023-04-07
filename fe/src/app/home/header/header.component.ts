import {Component, OnInit} from '@angular/core';
import {TokenService} from '../../service/token/token.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {CartService} from '../../service/cart.service';
import {CustomerService} from '../../service/customer.service';
import {CustomerDto} from '../../dto/customer-dto';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  // tslint:disable-next-line:ban-types
  roles: String[] = [];
  checkLogin = false;
  name: string | null | undefined;
  idAccount: string | null | undefined;
  itemCount = 0;
  idCustomer: string | null | undefined;
  customerInfo: CustomerDto = {};
  constructor(private tokenService: TokenService,
              private router: Router,
              private toast: ToastrService,
              private cartService: CartService,
              private customerService: CustomerService) {
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      this.idCustomer = this.tokenService.getIdCustomer();
      this.idAccount = this.tokenService.getIdAccount();
      this.cartService.getCount().subscribe(data => {
        this.itemCount = data;
      });
    }
  }

  logOut(): void {
    window.localStorage.clear();
    this.router.navigateByUrl('/').then(() => {
      location.reload();
    });
    this.toast.success('Đăng xuất thành công', ' Thông báo', {
      timeOut: 3000,
      extendedTimeOut: 1500
    });
  }

  getAllCustomer(): void {
    // @ts-ignore
    this.customerInfo = this.customerService.getAllCustomer(this.idCustomer).subscribe(data => {
      this.customerInfo = data;
    });
  }
}
