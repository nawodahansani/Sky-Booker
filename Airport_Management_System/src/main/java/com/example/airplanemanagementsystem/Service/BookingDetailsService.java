package com.example.airplanemanagementsystem.Service;

import com.example.airplanemanagementsystem.Dto.BookingDetailsDTO;
import com.example.airplanemanagementsystem.Dto.BookingDetailsRequestDTO;

public interface BookingDetailsService {
    BookingDetailsDTO createBookingDetails(BookingDetailsRequestDTO bookingDetailsRequestDTO);
    BookingDetailsDTO getBookingDetailsById(Long id);
    BookingDetailsDTO getBookingDetailsByBookingId(Long bookingId);
    BookingDetailsDTO updateBookingDetails(Long id, BookingDetailsRequestDTO bookingDetailsRequestDTO);
    boolean deleteBookingDetails(Long id);
    BookingDetailsDTO calculateBookingPrice(Long bookingId);
    BookingDetailsDTO updatePaymentStatus(Long id, String paymentStatus);
    BookingDetailsDTO getLatestBookingDetails();
}
