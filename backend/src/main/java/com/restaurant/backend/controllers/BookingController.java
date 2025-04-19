package com.restaurant.backend.controllers;

import com.restaurant.backend.services.BookingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    public BookingController(BookingService bookingService) {
    }
}
