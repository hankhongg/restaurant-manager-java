package com.restaurant.backend.controllers;

import com.restaurant.backend.services.StockinService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockinController {
    public StockinController(StockinService stockinService) {
    }
}
