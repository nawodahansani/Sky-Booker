package com.example.airplanemanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "packages")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String packageCode;
    private String packageName;
    private String imagePath;
    private String description;
    private double price;
}
