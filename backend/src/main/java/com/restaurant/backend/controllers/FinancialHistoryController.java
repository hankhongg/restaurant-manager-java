package com.restaurant.backend.controllers;

import com.restaurant.backend.services.FinancialHistoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinancialHistoryController {
    public FinancialHistoryController(FinancialHistoryService financialHistoryService) {
    }
}
