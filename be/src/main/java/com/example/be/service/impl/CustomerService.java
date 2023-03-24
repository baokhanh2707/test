package com.example.be.service.impl;

import com.example.be.dto.GetIdCustomer;
import com.example.be.model.Customer;
import com.example.be.repository.ICustomerRepository;
import com.example.be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Long findByAccount(String userName) {
        return customerRepository.findByAccount(userName);
    }

    @Override
    public Optional<Customer> findByIdCustomer(Long id) {
        return customerRepository.findByIdCustomer(id);
    }
}
