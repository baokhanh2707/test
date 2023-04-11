import {Component, OnInit} from '@angular/core';
import {CanCuocService} from '../service/can-cuoc.service';
import {ToastrService} from 'ngx-toastr';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  constructor(private canCuocService: CanCuocService, private toastrService: ToastrService, private router: Router) {
  }

  validationMessages = {
    maCanCuoc: [
      {type: 'required', message: 'Vui lòng nhập nội dung.'},
    ],
    tenCanCuoc: [
      {type: 'required', message: 'Vui lòng nhập nội dung.'},
    ],
    hangSanXuat: [
      {type: 'required', message: 'Vui lòng nhập nội dung.'},
    ],
    soLuong: [
      {type: 'required', message: 'Vui lòng nhập nội dung.'},
      {type: 'min', message: 'Vui lòng nhập số nguyên dương.'},
    ],
    donVi: [
      {type: 'required', message: 'Vui lòng nhập nội dung.'},
    ]
  };
  canCuocCreate = new FormGroup({
    maCanCuoc: new FormControl('', [Validators.required]),
    tenCanCuoc: new FormControl('', [Validators.required]),
    hangSanXuat: new FormControl('', [Validators.required]),
    soLuong: new FormControl('', [Validators.required, Validators.min(0)]),
    donVi: new FormControl('', [Validators.required]),
  });

  ngOnInit(): void {
  }

  saveCanCuoc(): void {
    const canCuoc = this.canCuocCreate.value;
    this.canCuocService.saveCanCuoc(canCuoc).subscribe(data => {
      this.toastrService.success('Thêm mới thành công!', 'Thông báo');
      this.router.navigateByUrl('/');
    }, error => {
      this.toastrService.error('Thêm mới không thành công.', 'Thông báo');
      console.log(error);
    });
  }
}
