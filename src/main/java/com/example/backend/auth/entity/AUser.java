package com.example.backend.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auserid")
    private int auserId;
    @Column(name = "ausername")
    private String auserName;
}
