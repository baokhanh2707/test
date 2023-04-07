package com.example.be.controller;

import com.example.be.dto.CustomerDto;
import com.example.be.model.Customer;
import com.example.be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("{idPay}")
    public ResponseEntity<?> getCustomer(@PathVariable("idPay") Long id) {
        List<CustomerDto> customerInfoList = customerService.getCustomer(id);
        return new ResponseEntity<>(customerInfoList, HttpStatus.OK);
    }

    @GetMapping("info/{id}")
    public ResponseEntity<?>getAllCustomer(@PathVariable("id") Long id){
        CustomerDto infoCustomer = customerService.getAllCustomer(id);
        if (infoCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(infoCustomer, HttpStatus.OK);

    }
}
