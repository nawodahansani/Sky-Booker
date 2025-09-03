package com.example.airplanemanagementsystem.Service.impl;


import com.example.airplanemanagementsystem.Dto.BookingDetailsDTO;
import com.example.airplanemanagementsystem.Dto.BookingDetailsRequestDTO;
import com.example.airplanemanagementsystem.Entity.Booking;
import com.example.airplanemanagementsystem.Entity.BookingDetails;
import com.example.airplanemanagementsystem.Repo.BookingDetailsRepository;
import com.example.airplanemanagementsystem.Repo.BookingRepository;
import com.example.airplanemanagementsystem.Service.BookingDetailsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

    private final BookingDetailsRepository bookingDetailsRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingDetailsServiceImpl(BookingDetailsRepository bookingDetailsRepository, BookingRepository bookingRepository) {
        this.bookingDetailsRepository = bookingDetailsRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public BookingDetailsDTO createBookingDetails(BookingDetailsRequestDTO requestDTO) {
        Booking booking = bookingRepository.findById(requestDTO.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + requestDTO.getBookingId()));

        // Check if booking details already exist
        bookingDetailsRepository.findByBooking(booking).ifPresent(existingDetails -> {
            throw new IllegalStateException("Booking details already exist for booking id: " + requestDTO.getBookingId());
        });

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setBooking(booking);
        bookingDetails.setPrice(requestDTO.getPrice());
        bookingDetails.setTax(requestDTO.getTax());
        // Total amount will be calculated by @PrePersist

        BookingDetails savedDetails = bookingDetailsRepository.save(bookingDetails);

        return convertToDTO(savedDetails);
    }

    @Override
    public BookingDetailsDTO getBookingDetailsById(Long id) {
        BookingDetails details = bookingDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking details not found with id: " + id));

        return convertToDTO(details);
    }

    @Override
    public BookingDetailsDTO getBookingDetailsByBookingId(Long bookingId) {
        BookingDetails details = bookingDetailsRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking details not found for booking id: " + bookingId));

        return convertToDTO(details);
    }

    @Override
    @Transactional
    public BookingDetailsDTO updateBookingDetails(Long id, BookingDetailsRequestDTO requestDTO) {
        BookingDetails details = bookingDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking details not found with id: " + id));

        details.setPrice(requestDTO.getPrice());
        details.setTax(requestDTO.getTax());
        // Total amount will be recalculated by @PreUpdate

        BookingDetails updatedDetails = bookingDetailsRepository.save(details);

        return convertToDTO(updatedDetails);
    }

    @Override
    @Transactional
    public boolean deleteBookingDetails(Long id) {
        if (bookingDetailsRepository.existsById(id)) {
            bookingDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public BookingDetailsDTO calculateBookingPrice(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + bookingId));

        BookingDetails details = bookingDetailsRepository.findByBooking(booking)
                .orElseThrow(() -> new EntityNotFoundException("Booking details not found for booking id: " + bookingId));

        // Recalculate price based on flight class
        BigDecimal basePrice;
        switch (booking.getFlightClass()) {
            case "First":
                basePrice = new BigDecimal("1000.00");
                break;
            case "Business":
                basePrice = new BigDecimal("500.00");
                break;
            default: // Economy
                basePrice = new BigDecimal("200.00");
                break;
        }

        details.setPrice(basePrice);
        details.setTax(basePrice.multiply(new BigDecimal("0.10"))); // 10% tax
        // Total amount will be recalculated by @PreUpdate

        BookingDetails updatedDetails = bookingDetailsRepository.save(details);

        return convertToDTO(updatedDetails);
    }

    @Override
    @Transactional
    public BookingDetailsDTO updatePaymentStatus(Long id, String paymentStatus) {
        BookingDetails details = bookingDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking details not found with id: " + id));

        details.setPaymentStatus(paymentStatus);
        BookingDetails updatedDetails = bookingDetailsRepository.save(details);

        return convertToDTO(updatedDetails);
    }

    private BookingDetailsDTO convertToDTO(BookingDetails details) {
        BookingDetailsDTO dto = new BookingDetailsDTO();
        dto.setId(details.getId());
        dto.setBookingId(details.getBooking().getId());
        dto.setPrice(details.getPrice());
        dto.setTax(details.getTax());
        dto.setTotalAmount(details.getTotalAmount());
        dto.setPaymentStatus(details.getPaymentStatus());

        return dto;
    }

    @Override
    public BookingDetailsDTO getLatestBookingDetails() {
        BookingDetails latestBookingDetails = bookingDetailsRepository.findTopByOrderByIdDesc();
        if (latestBookingDetails == null) {
            throw new EntityNotFoundException("No booking details found in the system");
        }
        return convertToDTO(latestBookingDetails);
    }
}