package com.example.be.repository;

import com.example.be.dto.OderDetailDto;
import com.example.be.model.OderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOderDetailRepository extends JpaRepository<OderDetail, Long> {
    @Query(value = "select oder_detail.quantity as quantity ,oder_detail.create_date as createDate ,oder_detail.price as price,oder_detail.status_pay as statusPay ,product.id_product as idProduct ,product.name_product as nameProduct,image_product.url as url from oder_detail join product on product.id_product = oder_detail.product_id_product join image_product on image_product.product_id_product = product.id_product where oder_detail.oder_id_pay = :id group by product.id_product ", nativeQuery = true)
    List<OderDetailDto> listOderDetailHistory(Long id);

}
