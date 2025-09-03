package com.example.airplanemanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Flight {
    @Id
    @Column(unique = true)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
    private String arrivalTime;
    private String departureTime;
    private String destination;
    private String operatingDays;
    private String origin;
    private String status;

    /*@OneToMany(mappedBy = "flight")
    private Set<Booking> bookings;*/



}
