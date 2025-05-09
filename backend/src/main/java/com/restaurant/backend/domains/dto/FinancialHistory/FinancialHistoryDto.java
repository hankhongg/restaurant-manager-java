package com.restaurant.backend.domains.dto.FinancialHistory;

import com.restaurant.backend.domains.dto.FinancialHistory.interfaces.FHType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialHistoryDto {
    private Integer id;

    private Instant finDate;

    private String description;

    private FHType type;

    private BigDecimal amount;

    private Integer referenceId;

    private String referenceType;
}
