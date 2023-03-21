package com.example.be.repository;

import com.example.be.dto.ImageByIdDto;
import com.example.be.dto.ProductDto;
import com.example.be.dto.ProductInfoById;
import com.example.be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT product.id_product as idProduct,product.name_product as nameProduct,year_product.id_year as idYear,year_product.name_year as nameYear,image_product.id as id,image_product.url as url,status_product.id_status_product as idStatusProduct,status_product.name_status_product as nameStatusProduct,type_product.id_type_product as idTypeProduct,type_product.name_type_product as nameTypeProduct FROM product join image_product on product.id_product = image_product.product_id_product join type_product on product.type_product_id=type_product.id_type_product join status_product on product.status_product_id=status_product.id_status_product join year_product on product.model_year_id=year_product.id_year where product.flag_delete=false group by product.id_product order by product.id_product "
            ,
            countQuery = "SELECT product.id_product as idProduct,product.name_product as nameProduct,year_product.id_year as idYear,year_product.name_year as nameYear,image_product.id as id,image_product.url as url,status_product.id_status_product as idStatusProduct,status_product.name_status_product as nameStatusProduct,type_product.id_type_product as idTypeProduct,type_product.name_type_product as nameTypeProduct FROM product join image_product on product.id_product = image_product.product_id_product join type_product on product.type_product_id=type_product.id_type_product join status_product on product.status_product_id=status_product.id_status_product join year_product on product.model_year_id=year_product.id_year where product.flag_delete=false group by product.id_product order by product.id_product",
             nativeQuery = true)
    Page<ProductDto> pageProduct(Pageable pageable);


    @Query(value = "SELECT product.id_product as idProduct,product.name_product as nameProduct,year_product.id_year as idYear,year_product.name_year as nameYear,image_product.id as id,image_product.url as url,status_product.id_status_product as idStatusProduct,status_product.name_status_product as nameStatusProduct,type_product.id_type_product as idTypeProduct,type_product.name_type_product as nameTypeProduct FROM product join image_product on product.id_product = image_product.product_id_product join type_product on product.type_product_id=type_product.id_type_product join status_product on product.status_product_id=status_product.id_status_product join year_product on product.model_year_id=year_product.id_year where product.flag_delete=false and year_product.name_year like %:nameYearProduct% and status_product.name_status_product like %:nameStatusProduct% and type_product.name_type_product like %:nameTypeProduct% group by product.id_product order by product.id_product",
            countQuery = "SELECT product.id_product as idProduct,product.name_product as nameProduct,year_product.id_year as idYear,year_product.name_year as nameYear,image_product.id as id,image_product.url as url,status_product.id_status_product as idStatusProduct,status_product.name_status_product as nameStatusProduct,type_product.id_type_product as idTypeProduct,type_product.name_type_product as nameTypeProduct FROM product join image_product on product.id_product = image_product.product_id_product join type_product on product.type_product_id=type_product.id_type_product join status_product on product.status_product_id=status_product.id_status_product join year_product on product.model_year_id=year_product.id_year where product.flag_delete=false and year_product.name_year like %:nameYearProduct% and status_product.name_status_product like %:nameStatusProduct% and type_product.name_type_product like %:nameTypeProduct% group by product.id_product order by product.id_product "
            , nativeQuery = true)
    Page<ProductDto> pageProductAndSearch(@Param("nameTypeProduct") String nameTypeProduct,
                                          @Param("nameStatusProduct") String nameStatusProduct,
                                          @Param("nameYearProduct") String nameYearProduct,
                                          Pageable pageable);
    @Query(value = "select product.engine as engine,product.exterior as exterior,product.interior as interior,product.mileage as mileage,product.name_product as nameProduct,product.power as power,product.price as price, year_product.name_year as nameYear from product join year_product on year_product.id_year = product.model_year_id where product.id_product = :idProduct",
            countQuery = "select product.engine as engine,product.exterior as exterior,product.interior as interior,product.mileage as mileage,product.name_product as nameProduct,product.power as power,product.price as price, year_product.name_year as nameYear from product join year_product on year_product.id_year = product.model_year_id where product.id_product = :idProduct",
            nativeQuery = true)
    ProductInfoById selectProductById(@Param("idProduct") Long idProduct);

    @Query (value = "select image_product.url from product join image_product on image_product.product_id_product = product.id_product where product.id_product = :idProduct",
            countQuery ="select image_product.url from product join image_product on image_product.product_id_product = product.id_product where product.id_product= :idProduct" , nativeQuery = true )
    List<ImageByIdDto>selectImageById(@Param("idProduct") Long idProduct);
}
