package com.example.be.repository;

import com.example.be.dto.CustomerDto;
import com.example.be.dto.GetIdCustomer;
import com.example.be.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByIdCustomer(Long id);

    Optional<Customer>findByEmail(String email);

    @Query(value = "select customer.id_customer from customer join account on account.id_account = customer.account_id_account where account.username_account =?1",nativeQuery = true)
    Long findByAccount(String userName);

    @Query(value = "select customer.name_customer as nameCustomer ,customer.email as email,customer.address as address,customer.phone_number as phoneNumber from customer where customer.id_customer= :id",nativeQuery = true)
    List<CustomerDto>getCustomer(Long id);
}
