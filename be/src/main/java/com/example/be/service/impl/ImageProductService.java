package com.example.be.service.impl;

import com.example.be.model.ImageProduct;
import com.example.be.repository.IImageProductRepository;
import com.example.be.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageProductService implements IImageService {
    @Autowired
    private IImageProductRepository imageProductRepository;
    @Override
    public List<ImageProduct> listImageProduct() {
        return imageProductRepository.findAll();
    }
}
