package com.example.airplanemanagementsystem.Service;

import com.example.airplanemanagementsystem.Dto.FlightDTO;

import java.util.List;

public interface FlightService {
    int SaveFlights(FlightDTO flightDTO);
    List<FlightDTO> getAllFlights();
    int DeleteFlight(String Id);
    int updateFlights(FlightDTO flightDTO);
    int SearchFlight(String flightId);
}
