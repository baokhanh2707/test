package com.example.be.repository;

import com.example.be.dto.OderDto;
import com.example.be.model.Oder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IOderRepository extends JpaRepository<Oder,Long> {
    @Query(value = "select oder.id_pay as idPay, oder.code_pay as codePay, oder.name_pay as namePay, oder.address_pay as addressPay, oder.total_cart as totalCart,oder.create_date as createDate from oder where oder.customer_id_customer= :id order by oder.id_pay DESC",
            countQuery = "select oder.id_pay as idPay, oder.code_pay as codePay, oder.name_pay as namePay, oder.address_pay as addressPay, oder.total_cart as totalCart,oder.create_date as createDate from oder where oder.customer_id_customer= :id order by oder.id_pay DESC",nativeQuery = true)
    Page<OderDto> listOderHistory(Long id, Pageable pageable);


}
