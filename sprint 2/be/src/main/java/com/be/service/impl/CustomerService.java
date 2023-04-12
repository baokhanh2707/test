package com.be.service.impl;

import com.be.dto.customer.GetIdCustomer;
import com.be.model.Customer;
import com.be.repository.customer.ICustomerRepository;
import com.be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Optional<Customer> findByIdCustomer(Long id) {
        return customerRepository.findByIdCustomer(id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<GetIdCustomer> findByAccount(String userName) {
        return customerRepository.findByAccount(userName);
    }


}
