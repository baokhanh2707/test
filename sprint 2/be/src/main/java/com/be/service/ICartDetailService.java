package com.be.service;

import com.be.dto.customer.CartDto;
import com.be.model.CartDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    List<CartDto> getAllCart(@Param("idCustomer") Long idCustomer);
    void updateAmountInCart(@Param("quantity") Long quantity, @Param("idCartDetail") Long idCartDetail);
    boolean existsByLaptopIdLaptopAndCartCustomerIdCustomer(Long idLaptop, Long idCustomer);
    Double getTotalMoneyCart(@Param("idCustomer") Long idCustomer);
    CartDetail save (CartDetail cartDetail);
    Optional<CartDetail> findByLaptopIdLaptopAndCartCustomerIdCustomer(Long idLaptop, Long idCustomer);
}
