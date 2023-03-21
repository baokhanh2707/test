package com.example.be.service.impl;
import com.example.be.model.account.Account;
import com.example.be.repository.account.IAccountRepository;
import com.example.be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;


    @Override
    public Optional<Account> findByUserName(String userName) {
        return iAccountRepository.findByUsername(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iAccountRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String userName) {
        return iAccountRepository.existsByUsername(userName);
    }
}
