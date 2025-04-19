package com.restaurant.backend.controllers;

import com.restaurant.backend.services.MenuItemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController {
    public MenuItemController(MenuItemService menuItemService) {
    }
}
