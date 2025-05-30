package com.restaurant.backend.domains.dto.Recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateManyRecipesDto {
    List<CreateRecipeDto> ingredients;
}
