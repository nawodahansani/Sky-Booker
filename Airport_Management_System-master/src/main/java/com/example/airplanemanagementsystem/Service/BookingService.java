package com.example.airplanemanagementsystem.Service;



import com.example.airplanemanagementsystem.Dto.BookingDTO;
import com.example.airplanemanagementsystem.Dto.BookingRequestDTO;
import com.example.airplanemanagementsystem.Dto.BookingResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO getBookingById(Long id);
    List<BookingDTO> getAllBookings();
    List<BookingDTO> getBookingsByDate(LocalDate date);
    List<BookingDTO> getBookingsByPassenger(String passengerName);
    BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingRequestDTO);
    boolean deleteBooking(Long id);
    String generateSeatNumber();
}
