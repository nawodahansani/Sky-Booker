package com.example.airplanemanagementsystem.Service;

import com.example.airplanemanagementsystem.Dto.PaymentRequestDTO;
import com.example.airplanemanagementsystem.Dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO createPaymentIntent(PaymentRequestDTO paymentRequest);
}
