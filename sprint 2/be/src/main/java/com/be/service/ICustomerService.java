package com.be.service;

import com.be.dto.customer.GetIdCustomer;
import com.be.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> findByIdCustomer(Long id);

    Optional<Customer>findByEmail(String email);

    Optional<GetIdCustomer>findByAccount(String userName);
}
