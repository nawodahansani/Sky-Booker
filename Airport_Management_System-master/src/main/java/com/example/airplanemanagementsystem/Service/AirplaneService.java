package com.example.airplanemanagementsystem.Service;

import com.example.airplanemanagementsystem.Dto.AirplaneDTO;
import com.example.airplanemanagementsystem.Dto.FlightDTO;

import java.util.List;
import java.util.UUID;

public interface AirplaneService {
    int SaveFlights(AirplaneDTO airplaneDTO);
    List<AirplaneDTO> getAllFlights();
    int DeleteFlight(UUID Id);
    int updateFlights(AirplaneDTO airplaneDTO);
}
