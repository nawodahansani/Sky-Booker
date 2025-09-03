package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDTO {
    private String flightNumber;
    private String aircraft;
    private String arrivalTime;
    private String departureTime;
    private String destination;
    private String operatingDays;
    private String origin;
    private String status;

}
