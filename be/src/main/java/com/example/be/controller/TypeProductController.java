package com.example.be.controller;

import com.example.be.model.ImageProduct;
import com.example.be.model.TypeProduct;
import com.example.be.service.IImageService;
import com.example.be.service.ITypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/type")
@CrossOrigin("*")
public class TypeProductController {
    @Autowired
    private ITypeProductService typeProductService;
    @GetMapping("")
    public ResponseEntity<List<TypeProduct>> getAllTypeProduct() {
        List<TypeProduct> typeProductList = typeProductService.listTypeProduct();
        return new ResponseEntity<>(typeProductList, HttpStatus.OK);
    }
}
