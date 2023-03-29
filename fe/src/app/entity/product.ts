import {TypeProduct} from './type-product';
import {YearProduct} from './year-product';
import {Status} from './status';

export interface Product {
  idProduct?: number;
  nameProduct?: string;
  mileage?: string;
  interior?: string;
  power?: string;
  engine?: string;
  price?: number;
  exterior?: string;
  flagDelete?: boolean;
  typeProductId?: TypeProduct;
  modelYear?: YearProduct;
  statusProduct?: Status;
  amountExist?: number;
}
