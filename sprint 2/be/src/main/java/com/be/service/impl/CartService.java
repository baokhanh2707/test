package com.be.service.impl;

import com.be.model.Cart;
import com.be.repository.product.ICartRepository;
import com.be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
