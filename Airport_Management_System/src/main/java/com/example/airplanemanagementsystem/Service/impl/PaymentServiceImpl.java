package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Dto.PaymentRequestDTO;
import com.example.airplanemanagementsystem.Dto.PaymentResponseDTO;
import com.example.airplanemanagementsystem.Entity.Payment;
import com.example.airplanemanagementsystem.Repo.PaymentRepository;
import com.example.airplanemanagementsystem.Service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.secret}")
    private String stripeSecretKey;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Override
    public PaymentResponseDTO createPaymentIntent(PaymentRequestDTO request) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) request.getAmount())
                    .setCurrency(request.getCurrency())
                    .setReceiptEmail(request.getEmail())
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            Payment payment = new Payment();
            payment.setBookingId(request.getBookingId());
            payment.setEmail(request.getEmail());
            payment.setAmount(request.getAmount());
            payment.setCurrency(request.getCurrency());
            payment.setStatus("Completed");
            payment.setPaymentIntentId(intent.getId());

            paymentRepository.save(payment);

            return new PaymentResponseDTO(intent.getClientSecret());
        } catch (StripeException e) {
            throw new RuntimeException("Stripe payment failed", e);
        }
    }
}
