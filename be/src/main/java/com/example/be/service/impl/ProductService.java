package com.example.be.service.impl;

import com.example.be.dto.ImageByIdDto;
import com.example.be.dto.ProductDto;
import com.example.be.dto.ProductInType;
import com.example.be.dto.ProductInfoById;
import com.example.be.dto.ProductHomeOne;
import com.example.be.model.Product;
import com.example.be.repository.IProductRepository;
import com.example.be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<ProductDto> pageProductAndSearch(String idTypeProduct, String idStatusProduct, String idYearProduct, Pageable pageable) {
        return productRepository.pageProductAndSearch(idTypeProduct,idStatusProduct,idYearProduct,pageable);
    }

    @Override
    public Page<ProductDto> pageProduct(Pageable pageable) {
        return productRepository.pageProduct(pageable);
    }

    @Override
    public ProductInfoById selectProductById(Long idProduct) {
        return productRepository.selectProductById(idProduct);
    }

    @Override
    public List<ImageByIdDto> selectImageById(Long idProduct) {
        return productRepository.selectImageById(idProduct);
    }

    @Override
    public List<ProductInType> selectProductByIdType(Long idProduct) {
        return productRepository.selectProductByIdType(idProduct);
    }

    @Override
    public ProductHomeOne selectProduct() {
        return productRepository.selectProduct();
    }

    @Override
    public Optional<Product> findByIdProduct(Long idProduct) {
        return productRepository.findById(idProduct);
    }
}
