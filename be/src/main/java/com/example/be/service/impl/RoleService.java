package com.example.be.service.impl;

import com.example.be.model.account.Role;
import com.example.be.model.account.RoleName;
import com.example.be.repository.account.IRolesRepository;
import com.example.be.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRolesService {
    @Autowired
    private IRolesRepository iRolesRepository;
    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return iRolesRepository.findByNameRole(roleName);
    }

    @Override
    public List<Role> findAll() {
        return iRolesRepository.findAllRole();
    }
}
