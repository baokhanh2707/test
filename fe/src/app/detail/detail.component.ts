import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {Product} from '../entity/product';
import {ActivatedRoute} from '@angular/router';
import {DetailDto} from '../dto/detail-dto';
import {Image} from '../dto/image';
import {ProductInType} from '../dto/product-in-type';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  productDto: DetailDto = {};
  imageList: Image [] = [];
  image: string | undefined;
  productInType: ProductInType [] = [];
  idProduct: string | null | undefined;
  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.paramMap.subscribe(data => {
      this.idProduct = data.get('id');
      {
        this.productService.getAllProductById(Number(this.idProduct)).subscribe(data2 => {
          this.productDto = data2;
          console.log(data);
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

  ngOnInit(): void {
    this.scroll();
  }

  scroll(): void {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
}
