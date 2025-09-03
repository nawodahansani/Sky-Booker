package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Dto.AirplaneDTO;
import com.example.airplanemanagementsystem.Dto.FlightDTO;
import com.example.airplanemanagementsystem.Dto.PackageDTO;
import com.example.airplanemanagementsystem.Entity.Airplane;
import com.example.airplanemanagementsystem.Entity.Packages;
import com.example.airplanemanagementsystem.Repo.AirplaneRepository;
import com.example.airplanemanagementsystem.Service.AirplaneService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int SaveFlights(AirplaneDTO airplaneDTO) {
        airplaneRepository.save(modelMapper.map(airplaneDTO, Airplane.class));
        return VarList.Created;
    }

    @Override
    public List<AirplaneDTO> getAllFlights() {
        List<Airplane> airplaneList = airplaneRepository.findAll();
        return airplaneList.stream()
                .map(airplane -> modelMapper.map(airplane, AirplaneDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int DeleteFlight(UUID Id) {
        if (airplaneRepository.existsById(Id)) {
            airplaneRepository.deleteById(Id);
            return VarList.Deleted;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateFlights(AirplaneDTO airplaneDTO) {
        if (airplaneRepository.existsById(airplaneDTO.getAirplaneId())) {
            airplaneRepository.save(modelMapper.map(airplaneDTO, Airplane.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }
}
