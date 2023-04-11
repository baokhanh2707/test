import {SanPham} from './san-pham';

export interface LoHang {
  id?: number;
  maLoHang?: string;
  sanPham?: SanPham;
  soLuong?: number;
  ngayNhapHang?: string;
  ngaySanXuat?: string;
  ngayHetHan?: string;
  flagDelete?: boolean;
}
