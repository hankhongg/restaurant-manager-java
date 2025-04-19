package com.restaurant.backend.controllers;

import com.restaurant.backend.services.ReceiptService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptDetailController {
    public ReceiptDetailController(ReceiptService receiptService) {
    }
}
