import {Component, OnInit} from '@angular/core';
import {SecurityService} from '../service/security/security.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, Validators} from '@angular/forms';
import {Title} from '@angular/platform-browser';
import {TokenService} from '../service/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // @ts-ignore
  signInForm: FormGroup;
  statusRole: any[] = [];

  constructor(private securityService: SecurityService,
              private tokenService: TokenService,
              private router: Router,
              private toast: ToastrService,
              private formBuilder: FormBuilder,
              private titleService: Title) {
    this.titleService.setTitle('Đăng nhập');
  }

  ngOnInit(): void {
    this.getFormLogin();
  }

  getFormLogin(): void {
    this.signInForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  login(): void {
    const signInForm = this.signInForm?.value;
    this.securityService.signIn(signInForm).subscribe(data => {
        this.tokenService.setToken(data.token);
        this.tokenService.setName(data.name);
        this.tokenService.setRole(data.roles);
        this.statusRole = data.roles;
        this.tokenService.setEmail(data.email);
        this.tokenService.setIdAccount(data.idAccount);
        this.tokenService.setIdCustomer(data.idCustomer);
        this.toast.success('Đăng nhập thành công.', 'Thông báo', {
          timeOut: 3000,
          extendedTimeOut: 1500
        });
        location.href = 'http://localhost:4200/';
        // @ts-ignore
        if (data.status === 202) {
          this.toast.error('Đăng nhập thất bại.', 'Thông báo', {});
        }
      }, error => {
        if (error.status === 403) {
          this.toast.error('Mật khẩu hoặc tài khoản không đúng vui lòng nhập lại.', 'Thông báo');
        }
      }
    )
    ;
  }
}
