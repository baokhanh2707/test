package com.example.be.service.impl;

import com.example.be.dto.CartDto;
import com.example.be.model.CartDetail;
import com.example.be.repository.ICartDetailRepository;
import com.example.be.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartDetailService implements ICartDetailService {
@Autowired
private ICartDetailRepository cartDetailRepository;
    @Override
    public List<CartDto> getAllCart(Long idCustomer) {
        return cartDetailRepository.getAllCart(idCustomer);
    }

    @Override
    public void updateAmountInCart(Long quantity, Long idCartDetail) {
        cartDetailRepository.updateAmountInCart(quantity,idCartDetail);

    }

    @Override
    public boolean existsByProductIdProductAndCartCustomerIdCustomer(Long idProduct, Long idCustomer) {
        return cartDetailRepository.existsByProductIdProductAndCartCustomerIdCustomer(idProduct,idCustomer);
    }

    @Override
    public Optional<CartDetail> findByProductIdProductAndCartCustomerIdCustomer(Long idProduct, Long idCustomer) {
        return cartDetailRepository.findByProductIdProductAndCartCustomerIdCustomer(idProduct,idCustomer);
    }

    @Override
    public Double getTotalMoneyCart(Long idCustomer) {
        return cartDetailRepository.getTotalMoneyCart(idCustomer);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }
}
