package com.restaurant.backend.controllers;

import com.restaurant.backend.services.RecipeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    public RecipeController(RecipeService recipeService) {
    }
}
