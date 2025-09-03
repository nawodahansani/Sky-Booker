package com.example.airplanemanagementsystem.Repo;


import com.example.airplanemanagementsystem.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDate(LocalDate bookingDate);
    List<Booking> findByPassengerNameContaining(String passengerName);
    List<Booking> findByFlightClass(String flightClass);
    List<Booking> findByBookingStatus(String status);
}