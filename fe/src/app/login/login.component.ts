import { Component, OnInit } from '@angular/core';
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
              private titleService: Title) { }

  ngOnInit(): void {
    this.getFormLogin();
  }
  getFormLogin(): void {
    this.signInForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      rememberMe: [false]
    });
  }

  login(): void {
    const signInForm = this.signInForm?.value;
    this.securityService.signIn(signInForm).subscribe(data => {
        console.log(data);
        if (data.token !== undefined) {
          if (this.signInForm?.value.rememberMe) {
            this.tokenService.rememberMe(data.roles, data.name, data.token);
            this.router.navigateByUrl('/header');
          } else {
            this.tokenService.setToken(data.token);
            this.tokenService.setName(data.name);
            this.tokenService.setRole(data.roles);
            this.statusRole = data.roles;
            this.tokenService.setEmail(data.email);
            this.tokenService.setIdAccount(data.idAccount);
            this.tokenService.setIdCustomer(data.idCustomer);
            this.toast.success('Đăng nhập thành công.', 'Thông báo');
            location.href = 'http://localhost:4200/';
          }
        }
        // @ts-ignore
        if (data.status === 202) {
          this.toast.error('Mật khẩu không đúng vui lòng nhập lại.', 'Thông báo', {
          });
        }
      }, error => {
        if (error.status === 403) {
          this.toast.error('Đăng nhập thất bại, vui lòng nhập lại.', 'Thông báo');
        }
      }
    )
    ;
  }
}
