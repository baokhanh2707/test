package com.example.be.service;

import com.example.be.dto.GetIdCustomer;
import com.example.be.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Long  findByAccount(String userName);
    Optional<Customer> findByIdCustomer(Long id);
}
