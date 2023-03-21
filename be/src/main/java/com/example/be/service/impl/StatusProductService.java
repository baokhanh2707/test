package com.example.be.service.impl;

import com.example.be.model.StatusProduct;
import com.example.be.repository.IStatusProductRepository;
import com.example.be.service.IStatusProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusProductService implements IStatusProductService {
    @Autowired
    private IStatusProductRepository statusProductRepository;
    @Override
    public List<StatusProduct> listStatusProduct() {
        return statusProductRepository.findAll();
    }
}
