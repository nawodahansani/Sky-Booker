package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDetailsDTO {
    private Long id;
    private Long bookingId;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal totalAmount;
    private String paymentStatus;
}
