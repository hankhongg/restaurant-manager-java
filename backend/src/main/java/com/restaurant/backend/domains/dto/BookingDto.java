package com.restaurant.backend.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {
    private Integer id;

    private EmployeeDto emp;

    private CustomerDto cus;

    private DiningTableDto tab;

    private Instant bkStime;

    private Instant bkOtime;

    private Short bkStatus;

    private Boolean isdeleted = false;
}
