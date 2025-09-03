package com.example.airplanemanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "flight_class", nullable = false)
    private String flightClass;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "booking_status")
    private String bookingStatus = "CONFIRMED";

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }
}