package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
    private UUID ticketId;
    private String flightNumber;
    private String destination;
    private String type;
    private double price;
    private int SeatNo;
}
