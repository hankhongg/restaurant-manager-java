package com.restaurant.backend.controllers;

import com.restaurant.backend.services.DiningTableService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiningTableController {
    public DiningTableController(DiningTableService diningTableService) {
    }
}
