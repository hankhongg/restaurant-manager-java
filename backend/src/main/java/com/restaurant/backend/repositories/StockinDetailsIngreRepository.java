package com.restaurant.backend.repositories;

import com.restaurant.backend.domains.entities.StockinDetailsIngre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockinDetailsIngreRepository extends JpaRepository<StockinDetailsIngre, Integer> {
}
