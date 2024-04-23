package com.carshop2.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions = new HashSet<>();
}