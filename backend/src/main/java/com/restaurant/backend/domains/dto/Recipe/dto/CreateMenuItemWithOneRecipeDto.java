package com.restaurant.backend.domains.dto.Recipe.dto;

import com.restaurant.backend.domains.dto.MenuItem.dto.CreateMenuItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuItemWithOneRecipeDto {
    private CreateMenuItemDto menuItem;
    private CreateRecipeDto recipe;
}
