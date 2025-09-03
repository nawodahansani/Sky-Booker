package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Dto.FlightDTO;
import com.example.airplanemanagementsystem.Entity.Flight;
import com.example.airplanemanagementsystem.Entity.Flight;
import com.example.airplanemanagementsystem.Entity.User;
import com.example.airplanemanagementsystem.Repo.FlightRepository;
import com.example.airplanemanagementsystem.Service.FlightService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int SaveFlights(FlightDTO flightDTO) {
        flightRepository.save(modelMapper.map(flightDTO, Flight.class));
        return VarList.Created;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        List<Flight> flightList = flightRepository.findAll();
        return flightList.stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int DeleteFlight(String Id) {
        if (flightRepository.existsById(Id)) {
            flightRepository.deleteById(Id);
            return VarList.Deleted;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateFlights(FlightDTO flightDTO) {
        if (flightRepository.existsById(flightDTO.getFlightNumber())) {
            flightRepository.save(modelMapper.map(flightDTO, Flight.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int SearchFlight(String flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        return flight.isPresent() ? VarList.Found : VarList.Not_Found;
    }




}