package com.example.airplanemanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tickets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketId;

    @ManyToOne
    @JoinColumn(name = "flight_number", nullable = false)
    private Flight flight;

    private String destination;
    private String type;
    private double price;

    // Optional: Associate tickets with a package if applicable
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Packages packageInfo;


}
