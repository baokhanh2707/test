package com.example.be.service.impl;

import com.example.be.model.TypeProduct;
import com.example.be.repository.ITypeProductRepository;
import com.example.be.service.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
@Service
public class TypeProductService implements ITypeProductService {
    @Autowired
    private ITypeProductRepository typeProductRepository;
    @Override
    public List<TypeProduct> listTypeProduct() {
        return typeProductRepository.findAll();
    }
}
