package com.restaurant.backend.controllers;

import com.restaurant.backend.services.AccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    public AccountController(AccountService accountService) {
    }
}
