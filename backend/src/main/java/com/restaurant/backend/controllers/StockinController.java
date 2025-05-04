package com.restaurant.backend.controllers;

import com.restaurant.backend.domains.dto.StockIn.StockInDTO;
import com.restaurant.backend.services.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock-in")
public class StockInController {

    private final StockInService stockInService;

    @Autowired
    public StockInController(StockInService stockInService) {
        this.stockInService = stockInService;
    }

    @PostMapping
    public ResponseEntity<StockInDTO> createStockIn(@RequestBody StockInDTO stockInDTO) {
        StockInDTO createdStockIn = stockInService.createStockIn(stockInDTO);
        return new ResponseEntity<>(createdStockIn, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StockInDTO>> getAllStockIns() {
        List<StockInDTO> stockIns = stockInService.getAllStockIns();
        return new ResponseEntity<>(stockIns, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockInDTO> getStockInById(@PathVariable Integer id) {
        try {
            StockInDTO stockIn = stockInService.getStockInById(id);
            return new ResponseEntity<>(stockIn, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockInDTO> updateStockIn(@PathVariable Integer id, @RequestBody StockInDTO stockInDTO) {
        try {
            StockInDTO updatedStockIn = stockInService.updateStockIn(id, stockInDTO);
            return new ResponseEntity<>(updatedStockIn, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockIn(@PathVariable Integer id) {
        try {
            stockInService.deleteStockIn(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}