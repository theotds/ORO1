package com.carshop2.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "car_model_parts",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<Part> parts = new HashSet<>();
}