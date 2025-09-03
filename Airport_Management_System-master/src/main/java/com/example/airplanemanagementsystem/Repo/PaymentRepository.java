package com.example.airplanemanagementsystem.Repo;

import com.example.airplanemanagementsystem.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
