package com.example.airplanemanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "payment_status")
    private String paymentStatus = "PENDING";

    // Method to calculate total amount
    @PrePersist
    @PreUpdate
    public void calculateTotalAmount() {
        if (price != null && tax != null) {
            totalAmount = price.add(tax);
        }
    }
}