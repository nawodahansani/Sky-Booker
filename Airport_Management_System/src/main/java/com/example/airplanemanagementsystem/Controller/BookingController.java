package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.*;
import com.example.airplanemanagementsystem.Service.BookingDetailsService;
import com.example.airplanemanagementsystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin("*")
public class BookingController {

    private final BookingService bookingService;
    private final BookingDetailsService bookingDetailsService;

    @Autowired
    public BookingController(BookingService bookingService, BookingDetailsService bookingDetailsService) {
        this.bookingService = bookingService;
        this.bookingDetailsService = bookingDetailsService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        System.out.println("Received booking request: " + bookingRequestDTO);
        BookingResponseDTO responseDTO = bookingService.createBooking(bookingRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        BookingResponseDTO responseDTO = bookingService.getBookingById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("GetAll")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/date")
    public ResponseEntity<List<BookingDTO>> getBookingsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BookingDTO> bookings = bookingService.getBookingsByDate(date);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/passenger")
    public ResponseEntity<List<BookingDTO>> getBookingsByPassenger(@RequestParam String name) {
        List<BookingDTO> bookings = bookingService.getBookingsByPassenger(name);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
            @PathVariable Long id, @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO responseDTO = bookingService.updateBooking(id, bookingRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/generate-seat")
    public ResponseEntity<Map<String, String>> generateSeatNumber() {
        String seatNumber = bookingService.generateSeatNumber();
        Map<String, String> response = new HashMap<>();
        response.put("seatNumber", seatNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> getBookingDetailsByBookingId(@PathVariable Long bookingId) {
        BookingDetailsDTO detailsDTO = bookingDetailsService.getBookingDetailsByBookingId(bookingId);
        return ResponseEntity.ok(detailsDTO);
    }

    @PutMapping("/details/{id}/payment-status")
    public ResponseEntity<BookingDetailsDTO> updatePaymentStatus(
            @PathVariable Long id, @RequestParam String status) {
        BookingDetailsDTO detailsDTO = bookingDetailsService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(detailsDTO);
    }
}