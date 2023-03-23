package com.example.be.service;

import com.example.be.dto.GetIdCustomer;
import com.example.be.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Optional<GetIdCustomer> findByAccount(String userName);
    Optional<Customer> findByIdCustomer(Long id);
}
