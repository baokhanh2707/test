package com.example.be.service;

import com.example.be.dto.*;
import com.example.be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<ProductDto>pageProductAndSearch(@Param("idTypeProduct") String nameTypeProduct ,
                                         @Param("idStatusProduct") String nameStatusProduct,
                                         @Param("idYearProduct") String nameYearProduct,
                                         Pageable pageable );
    Page<ProductDto>pageProduct(Pageable pageable);
    List<Product>getAllProduct();
    ProductInfoById selectProductById(@Param("idProduct") Long idProduct);
    List<ImageByIdDto> selectImageById(@Param("idProduct") Long idProduct);
    List<ProductInType>selectProductByIdType(@Param("idProduct") Long idProduct);
    ProductHomeOne selectProduct();
    Optional<Product>findByIdProduct(Long idProduct);
    Optional<AmountProductDto> getAmountExist(Integer id);
    void save(Product product);
}
