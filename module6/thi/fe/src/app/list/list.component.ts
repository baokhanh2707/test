import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {CanCuoc} from '../entity/can-cuoc';
import {CanCuocJson} from '../entity/can-cuoc-json';
import {CanCuocService} from '../service/can-cuoc.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  page = 0;
  canCuocJson!: CanCuocJson;
  canCuocList: CanCuoc[] = [];
  tenCanCuocSearch = '';
  hangSanXuatSearch = '';

  constructor(private toastrService: ToastrService, private canCuocService: CanCuocService) {
  }

  ngOnInit(): void {
    this.getAllCanCuoc(this.tenCanCuocSearch, this.hangSanXuatSearch, true);
  }

  getAllCanCuoc(tenCanCuocSearch: string, hangSanXuatSearch: string, flag: boolean): void {
    if (!flag) {
      this.page = 0;
    }
    this.tenCanCuocSearch = tenCanCuocSearch;
    this.hangSanXuatSearch = hangSanXuatSearch;
    this.canCuocService.getAllAndSearchCanCuoc(tenCanCuocSearch.trim(), hangSanXuatSearch.trim(), this.page).subscribe(data => {
      console.log(data);
      this.canCuocJson = data;
      console.log(data);
    }, error => {
    });
  }

  private showToastrError(): void {
    this.toastrService.error('Không có kết quả cần tìm.', 'Thông báo');
  }

  gotoPage(pageNumber: number): void {
    this.page = pageNumber;
    this.ngOnInit();
  }
}
