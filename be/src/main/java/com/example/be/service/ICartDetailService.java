package com.example.be.service;

import com.example.be.dto.CartDto;
import com.example.be.model.CartDetail;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    List<CartDto> getAllCart(@Param("idCustomer") Long idCustomer);
    void updateAmountInCart(@Param("quantity") Long quantity, @Param("idCartDetail") Long idCartDetail);
    boolean existsByProductIdProductAndCartCustomerIdCustomer(Long idProduct, Long idCustomer);
    Optional<CartDetail> findByProductIdProductAndCartCustomerIdCustomer(Long idProduct, Long idCustomer);
    Double getTotalMoneyCart(@Param("idCustomer") Long idCustomer);
    CartDetail save (CartDetail cartDetail);
}
