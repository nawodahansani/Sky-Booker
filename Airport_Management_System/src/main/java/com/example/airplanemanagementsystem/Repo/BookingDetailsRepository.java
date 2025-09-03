package com.example.airplanemanagementsystem.Repo;


import com.example.airplanemanagementsystem.Entity.Booking;
import com.example.airplanemanagementsystem.Entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
    Optional<BookingDetails> findByBooking(Booking booking);
    Optional<BookingDetails> findByBookingId(Long bookingId);
    BookingDetails findTopByOrderByIdDesc();
}