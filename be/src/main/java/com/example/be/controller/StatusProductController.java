package com.example.be.controller;
import com.example.be.model.StatusProduct;
import com.example.be.service.IStatusProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/status")
@CrossOrigin("*")
public class StatusProductController {
    @Autowired
    private IStatusProductService statusProductService;
    @GetMapping("")
    public ResponseEntity<List<StatusProduct>> getAllStatusProduct() {
        List<StatusProduct> statusProducts = statusProductService.listStatusProduct();
        return new ResponseEntity<>(statusProducts, HttpStatus.OK);
    }
}
