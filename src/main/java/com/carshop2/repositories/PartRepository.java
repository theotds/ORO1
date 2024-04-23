package com.carshop2.repositories;

import com.carshop2.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByPriceBetween(double minPrice, double maxPrice);

    Optional<Part> findByName(String name);
}