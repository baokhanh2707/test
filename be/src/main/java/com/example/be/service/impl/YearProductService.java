package com.example.be.service.impl;

import com.example.be.model.YearProduct;
import com.example.be.repository.IYearProductRepository;
import com.example.be.service.IYearProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class YearProductService implements IYearProductService {
    @Autowired
    private IYearProductRepository yearProductRepository;
    @Override
    public List<YearProduct> listYearProduct() {
        return yearProductRepository.findAll();
    }
}
