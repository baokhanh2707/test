package com.be.repository.customer;

import com.be.dto.customer.GetIdCustomer;
import com.be.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByIdCustomer(Long id);

    Optional<Customer>findByEmail(String email);
@Query(value = "select customer.id_customer from customer join account on account.id_account = customer.account_id_account where account.username_account =?1",nativeQuery = true)
    Optional<GetIdCustomer>findByAccount(String userName);

}
