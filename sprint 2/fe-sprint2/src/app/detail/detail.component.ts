import {Component, OnInit} from '@angular/core';
import {HomeService} from '../service/home/home.service';
import {ActivatedRoute} from '@angular/router';
import {Product} from '../entity/product/product';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  laptopList: Product = {};
  laptopType: Product[] = [];

  constructor(private homeService: HomeService, private activatedRoute: ActivatedRoute, private toast: ToastrService) {
    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      {
        // tslint:disable-next-line:no-shadowed-variable
        this.homeService.detailLapTop(Number(id)).subscribe(data => {
          this.laptopList = data;
        });
      }
    });
    this.activatedRoute.paramMap.subscribe(data => {
      const id = data.get('id');
      {
        // tslint:disable-next-line:no-shadowed-variable
        this.homeService.getListTypeProduct(Number(id)).subscribe(data => {
          this.laptopType = data;
        });
      }
    });
  }


  ngOnInit(): void {
  }

}
