import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CanCuoc} from '../entity/can-cuoc';

@Injectable({
  providedIn: 'root'
})
export class CanCuocService {

  constructor(private http: HttpClient) {
  }

  getAllAndSearchCanCuoc(tenCanCuocSearch: string, hangSanXuatSearch: string, page: number): Observable<any> {
    return this.http.get<any>('http://localhost:8080/can-cuoc?tenCanCuocSearch='
      + tenCanCuocSearch + '&hangSanXuatSearch=' + hangSanXuatSearch + '&page=' + page);
  }

  getAllCanCuoc(page: number): Observable<any> {
    return this.http.get<any>('http://localhost:8080/can-cuoc?page=' + page);
  }
  saveCanCuoc(canCuoc: CanCuoc): Observable<CanCuoc> {
    return this.http.post<CanCuoc>('http://localhost:8080/can-cuoc/save', canCuoc);
  }
}

