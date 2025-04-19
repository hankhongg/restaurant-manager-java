package com.restaurant.backend.controllers;

import com.restaurant.backend.services.CustomerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    public CustomerController(CustomerService customerService) {
    }
}
