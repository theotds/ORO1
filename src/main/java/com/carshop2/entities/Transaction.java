package com.carshop2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    public Transaction(User user, Part part) {
        this.user=user;
        this.part=part;
    }

    public Transaction() {

    }
}