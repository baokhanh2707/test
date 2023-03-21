package com.example.be.service;
import com.example.be.model.account.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByUserName(String userName);
    Boolean existsByEmail(String email);
    Boolean existsByUsername (String userName);
//    Account save(Account account);
}
