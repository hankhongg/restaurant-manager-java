package com.restaurant.backend.domains.dto.StockIn;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockInDetailsDrinkOtherDTO {
    @JsonProperty("stockInId")
    private Integer stockInId;

    @NotNull(message = "itemId is required")
    @JsonProperty("itemId")
    private Integer itemId;

    @NotNull(message = "quantityUnits is required")
    @JsonProperty("quantityUnits")
    private Double quantityUnits;

    @NotNull(message = "cPrice is required")
    @JsonProperty("cPrice")
    private Double cPrice;

    @JsonProperty("totalCPrice")
    private Double totalCPrice;
}