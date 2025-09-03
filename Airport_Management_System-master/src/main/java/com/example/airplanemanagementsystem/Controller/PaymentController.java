package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.PaymentRequestDTO;
import com.example.airplanemanagementsystem.Dto.PaymentResponseDTO;
import com.example.airplanemanagementsystem.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/payments")
@RestController
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = paymentService.createPaymentIntent(request);
        return ResponseEntity.ok(response);
    }
}
