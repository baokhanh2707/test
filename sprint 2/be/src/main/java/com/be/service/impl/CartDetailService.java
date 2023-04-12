package com.be.service.impl;

import com.be.dto.customer.CartDto;
import com.be.model.CartDetail;
import com.be.repository.product.ICartDetailRepository;
import com.be.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ICartDetailRepository iCardDetailRepository;

    @Override
    public List<CartDto> getAllCart(Long idCustomer) {
        return iCardDetailRepository.getAllCart(idCustomer);
    }

    @Override
    public void updateAmountInCart(Long quantity, Long idCartDetail) {
        iCardDetailRepository.updateAmountInCart(quantity, idCartDetail);
    }

    @Override
    public boolean existsByLaptopIdLaptopAndCartCustomerIdCustomer(Long idLaptop, Long idCustomer) {
        return iCardDetailRepository.existsByLaptopIdLaptopAndCartCustomerIdCustomer(idLaptop,idCustomer);
    }

    @Override
    public Double getTotalMoneyCart(Long idCustomer) {
        return iCardDetailRepository.getTotalMoneyCart(idCustomer);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return iCardDetailRepository.save(cartDetail);
    }

    @Override
    public Optional<CartDetail> findByLaptopIdLaptopAndCartCustomerIdCustomer(Long idLaptop, Long idCustomer) {
        return iCardDetailRepository.findByLaptopIdLaptopAndCartCustomerIdCustomer(idLaptop,idCustomer);
    }

}
