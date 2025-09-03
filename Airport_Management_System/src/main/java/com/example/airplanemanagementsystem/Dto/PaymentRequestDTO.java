package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {
    private String bookingId;
    private String email;
    private int amount; // cents
    private String currency;
}
