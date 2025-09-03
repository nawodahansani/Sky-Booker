package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Dto.*;
import com.example.airplanemanagementsystem.Entity.Booking;
import com.example.airplanemanagementsystem.Entity.BookingDetails;
import com.example.airplanemanagementsystem.Repo.BookingRepository;
import com.example.airplanemanagementsystem.Service.BookingDetailsService;
import com.example.airplanemanagementsystem.Service.BookingService;
import com.example.airplanemanagementsystem.Service.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingDetailsService bookingDetailsService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingDetailsService bookingDetailsService) {
        this.bookingRepository = bookingRepository;
        this.bookingDetailsService = bookingDetailsService;
    }
    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        // Create and save the booking
        Booking booking = new Booking();
        booking.setBookingDate(bookingRequestDTO.getBookingDate());
        booking.setPassengerName(bookingRequestDTO.getPassengerName());
        booking.setFlightClass(bookingRequestDTO.getFlightClass());
        booking.setPaymentMethod(bookingRequestDTO.getPaymentMethod());
        booking.setDescription(bookingRequestDTO.getPackageName());
        booking.setEmail(bookingRequestDTO.getEmail());

        // Generate seat number if not provided
        String seatNumber = bookingRequestDTO.getSeatNumber();
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            seatNumber = generateSeatNumber();
        }
        booking.setSeatNumber(seatNumber);

        Booking updatedBooking = bookingRepository.save(booking);

        emailService.sendBookingConfirmation(updatedBooking.getEmail(), updatedBooking.getPassengerName());


        // Create booking details with calculated price based on class
        BookingDetailsRequestDTO detailsRequestDTO = new BookingDetailsRequestDTO();
        detailsRequestDTO.setBookingId(updatedBooking.getId());

        // Calculate price based on flight class
        BigDecimal basePrice;
        switch (updatedBooking.getFlightClass()) {
            case "First":
                basePrice = new BigDecimal("10000.00");
                break;
            case "Business":
                basePrice = new BigDecimal("5000.00");
                break;
            default: // Economy
                basePrice = new BigDecimal("3000.00");
                break;
        }

        detailsRequestDTO.setPrice(basePrice);
        detailsRequestDTO.setTax(basePrice.multiply(new BigDecimal("0.10"))); // 10% tax

        BookingDetailsDTO detailsDTO = bookingDetailsService.createBookingDetails(detailsRequestDTO);

        // Create response DTO
        return createBookingResponseDTO(updatedBooking, detailsDTO);
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));

        BookingDetailsDTO detailsDTO = bookingDetailsService.getBookingDetailsByBookingId(id);

        return createBookingResponseDTO(booking, detailsDTO);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByDate(LocalDate date) {
        return bookingRepository.findByBookingDate(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByPassenger(String passengerName) {
        return bookingRepository.findByPassengerNameContaining(passengerName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));

        booking.setBookingDate(bookingRequestDTO.getBookingDate());
        booking.setPassengerName(bookingRequestDTO.getPassengerName());
        booking.setFlightClass(bookingRequestDTO.getFlightClass());
        booking.setPaymentMethod(bookingRequestDTO.getPaymentMethod());

        if (bookingRequestDTO.getSeatNumber() != null && !bookingRequestDTO.getSeatNumber().trim().isEmpty()) {
            booking.setSeatNumber(bookingRequestDTO.getSeatNumber());
        }

        Booking updatedBooking = bookingRepository.save(booking);
        emailService.sendBookingConfirmation(updatedBooking.getEmail(), updatedBooking.getPassengerName());


        // Update booking details if flight class has changed
        BookingDetailsDTO detailsDTO = bookingDetailsService.calculateBookingPrice(id);

        return createBookingResponseDTO(updatedBooking, detailsDTO);
    }

    @Override
    @Transactional
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String generateSeatNumber() {
        // Generate a random seat number in format like A12, B05, etc.
        String[] rows = {"A", "B", "C", "D", "E", "F"};
        Random random = new Random();
        String row = rows[random.nextInt(rows.length)];
        int seatNum = random.nextInt(30) + 1; // Seats 1-30

        return row + String.format("%02d", seatNum);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        BeanUtils.copyProperties(booking, dto);
        return dto;
    }

    private BookingResponseDTO createBookingResponseDTO(Booking booking, BookingDetailsDTO detailsDTO) {
        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setId(booking.getId());
        responseDTO.setBookingDate(booking.getBookingDate());
        responseDTO.setPassengerName(booking.getPassengerName());
        responseDTO.setFlightClass(booking.getFlightClass());
        responseDTO.setPaymentMethod(booking.getPaymentMethod());
        responseDTO.setSeatNumber(booking.getSeatNumber());
        responseDTO.setBookingStatus(booking.getBookingStatus());
        responseDTO.setBookingDetails(detailsDTO);

        return responseDTO;
    }
}
