package com.carshop2.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions = new HashSet<>();

    public User(String mail) {
        email = mail;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}