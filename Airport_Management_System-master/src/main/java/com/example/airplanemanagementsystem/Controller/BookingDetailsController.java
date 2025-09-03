package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.BookingDetailsDTO;
import com.example.airplanemanagementsystem.Service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking-details")
@CrossOrigin("*")
public class BookingDetailsController {

    private final BookingDetailsService bookingDetailsService;

    @Autowired
    public BookingDetailsController(BookingDetailsService bookingDetailsService) {
        this.bookingDetailsService = bookingDetailsService;
    }

    @GetMapping("/latest")
    public ResponseEntity<BookingDetailsDTO> getLatestBookingDetails() {
        BookingDetailsDTO latestBookingDetails = bookingDetailsService.getLatestBookingDetails();
        return ResponseEntity.ok(latestBookingDetails);
    }
}
