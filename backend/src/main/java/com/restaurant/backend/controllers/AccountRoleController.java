package com.restaurant.backend.controllers;

import com.restaurant.backend.services.AccountRoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRoleController {
    public AccountRoleController(AccountRoleService accountRoleService) {
    }
}
