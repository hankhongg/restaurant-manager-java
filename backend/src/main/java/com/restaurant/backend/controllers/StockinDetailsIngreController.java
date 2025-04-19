package com.restaurant.backend.controllers;

import com.restaurant.backend.services.StockinDetailsIngreService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockinDetailsIngreController {
    public StockinDetailsIngreController(StockinDetailsIngreService stockinDetailsIngreService) {
    }

}
