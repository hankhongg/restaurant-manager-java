package com.restaurant.backend.services.impl;

import com.restaurant.backend.domains.entities.AccountRole;
import com.restaurant.backend.repositories.AccountRoleRepository;
import com.restaurant.backend.services.AccountRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    private final AccountRoleRepository accountRoleRepository;
    public AccountRoleServiceImpl(AccountRoleRepository accountRoleRepository) {
        this.accountRoleRepository = accountRoleRepository;
    }
    public AccountRole save(AccountRole accountRole) {
        return this.accountRoleRepository.save(accountRole);
    }
    public Optional<AccountRole> findById(int id) {
        return this.accountRoleRepository.findById(id);
    }
}
