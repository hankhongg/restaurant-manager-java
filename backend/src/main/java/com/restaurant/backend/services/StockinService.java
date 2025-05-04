package com.restaurant.backend.services;

import com.restaurant.backend.domains.dto.StockIn.StockInDTO;

import java.util.List;

public interface StockInService {
    StockInDTO createStockIn(StockInDTO stockInDTO);

    StockInDTO updateStockIn(Integer id, StockInDTO stockInDTO);

    void deleteStockIn(Integer id);

    List<StockInDTO> getAllStockIns();

    StockInDTO getStockInById(Integer id);
}