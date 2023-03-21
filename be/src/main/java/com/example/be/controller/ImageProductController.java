package com.example.be.controller;

import com.example.be.model.ImageProduct;
import com.example.be.model.YearProduct;
import com.example.be.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/image")
@CrossOrigin("*")
public class ImageProductController {
    @Autowired
    private IImageService iImageService;
    @GetMapping("")
    public ResponseEntity<List<ImageProduct>> getAllImageProduct() {
        List<ImageProduct> imageProductList = iImageService.listImageProduct();
        return new ResponseEntity<>(imageProductList, HttpStatus.OK);
    }
}
