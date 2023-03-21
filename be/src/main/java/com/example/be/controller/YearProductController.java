package com.example.be.controller;

import com.example.be.model.YearProduct;
import com.example.be.service.IYearProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/yearProduct")
@CrossOrigin("*")
public class YearProductController {
    @Autowired
    private IYearProductService yearProductService;

    @GetMapping("")
    public ResponseEntity<List<YearProduct>> getAllYearProduct() {
        List<YearProduct> yearProductList = yearProductService.listYearProduct();
        return new ResponseEntity<>(yearProductList, HttpStatus.OK);
    }
}
