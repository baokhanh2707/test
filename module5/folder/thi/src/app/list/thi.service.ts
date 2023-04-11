import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoHang} from '../lo-hang';
import {SanPham} from '../san-pham';

@Injectable({
  providedIn: 'root'
})
export class ThiService {
  URL_SANPHAM = 'http://localhost:8080/san-pham';
  URL_LOHANG = 'http://localhost:8080/lo-hang';

  constructor(private http: HttpClient) {
  }

  pageLoHang(day1: string, day2: string, idSanPham: number , page: number): Observable<any> {
    // if (idSanPham === -1) {
    //   return this.http.get<any>('http://localhost:8080/lo-hang?day1=' + day1 + '&day2' + day2  + '&page=' + page);
    // } else {
      return this.http.get<any>('http://localhost:8080/lo-hang?day1=' + day1 + '&day2' + day2 + '&idSanPham' + idSanPham + '&page=' + page);
    }
  getSanPham(): Observable<SanPham[]> {
    return this.http.get<SanPham[]>(this.URL_SANPHAM);
  }
  // save(loHang: LoHang): Observable<LoHang> {
  //   return this.http.post<LoHang>(this.URL_LOHANG, loHang);
  // }
  //
  // delete(id: number | undefined): Observable<LoHang> {
  //   return this.http.delete<LoHang>(this.URL_LOHANG + '/' + id);
  // }
  //
  // edit(loHang: LoHang): Observable<LoHang> {
  //   return this.http.patch<LoHang>(this.URL_LOHANG + '/' + loHang.id, loHang);
  //
  // }
  //
  // findById(id: number): Observable<LoHang> {
  //   return this.http.get<LoHang>(this.URL_LOHANG + '/' + id);
  // }
}
