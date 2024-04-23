package com.carshop2.repositories;

import com.carshop2.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    long countByPartId(Long partId);
    long countByUserId(Long userId);
}