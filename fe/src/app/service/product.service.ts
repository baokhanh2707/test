import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  URL_PRODUCT = 'http://localhost:8080/api/public/image';
  TYPE_PRODUCT = 'http://localhost:8080/api/public/type';
  YEAR_PRODUCT = 'http://localhost:8080/api/public/yearProduct';
  STATUS_PRODUCT = 'http://localhost:8080/api/public/status';
  PRODUCT = 'http://localhost:8080/api/public/product';
  PRODUCT_BY_ID = 'http://localhost:8080/api/public/product/detail?idProduct=';
  IMAGE_BY_ID = 'http://localhost:8080/api/public/product/image?idProduct=';
  PRODUCT_BY_ID_TYPE = 'http://localhost:8080/api/public/product/related?idProduct=';
  PRODUCT_ONE_HOME = 'http://localhost:8080/api/public/product/oneProduct';

  constructor(private httpClient: HttpClient) {
  }
  getProduct(size: number): Observable<any>{
    return this.httpClient.get<any>(this.PRODUCT + '?size' + size);
  }
  getAllProduct(typeProduct: string, statusProduct: string, yearProduct: string, size: number): Observable<any> {
    if (typeProduct == null && statusProduct == null && yearProduct == null) {
      return this.httpClient.get<any>(this.PRODUCT + '?size' + size);
    } else {
      const url = this.PRODUCT + '?size=' + size + '&nameTypeProduct=' + typeProduct +
        '&nameStatusProduct=' + statusProduct + '&nameYearProduct=' + yearProduct;

      return this.httpClient.get<any>(url);
    }
  }

  getAllTypeProduct(): Observable<any> {
    return this.httpClient.get<any>(this.TYPE_PRODUCT);
  }

  getAllUrlProduct(): Observable<any> {
    return this.httpClient.get<any>(this.URL_PRODUCT);
  }

  getYearProduct(): Observable<any> {
    return this.httpClient.get<any>(this.YEAR_PRODUCT);
  }

  getAllStatusProduct(): Observable<any> {
    return this.httpClient.get<any>(this.STATUS_PRODUCT);
  }

  getAllProductById(idProduct: number): Observable<any> {
    return this.httpClient.get<any>(this.PRODUCT_BY_ID + idProduct);
  }
  getAllImageById(idProduct: number): Observable<any>{
    return this.httpClient.get<any>(this.IMAGE_BY_ID + idProduct);
  }
  getAllProductByIdType(idProduct: number): Observable<any> {
    return this.httpClient.get<any>(this.PRODUCT_BY_ID_TYPE + idProduct);
  }
  getProductOneHome(): Observable<any> {
    return this.httpClient.get<any>(this.PRODUCT_ONE_HOME);
  }
}
