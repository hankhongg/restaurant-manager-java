package com.restaurant.backend.controllers;


import com.restaurant.backend.services.EmployeeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    public EmployeeController(EmployeeService employeeService) {
    }
}
