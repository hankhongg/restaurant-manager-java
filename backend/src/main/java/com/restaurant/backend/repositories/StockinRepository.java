package com.restaurant.backend.repositories;

import com.restaurant.backend.domains.entities.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Integer> {
}