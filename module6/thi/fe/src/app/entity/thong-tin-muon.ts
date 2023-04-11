import {CanCuoc} from './can-cuoc';
import {NhanVien} from './nhan-vien';

export interface ThongTinMuon {
  id?: number;
  soLuongMuon?: number;
  ngayMuon?: string;
  ngayTra?: string;
  cancuoc: CanCuoc;
  nhanvien: NhanVien;
}
