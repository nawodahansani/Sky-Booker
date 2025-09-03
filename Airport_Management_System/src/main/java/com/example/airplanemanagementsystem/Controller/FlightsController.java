package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.ResponseDTO;
import com.example.airplanemanagementsystem.Dto.FlightDTO;
import com.example.airplanemanagementsystem.Service.FlightService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flight")
@CrossOrigin("*")
public class FlightsController {
    private final FlightService flightService;

    public FlightsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllFlights(){
        try {
            List<FlightDTO> flights = flightService.getAllFlights();
            ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Success", flights);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> saveFlights(@Valid @RequestBody FlightDTO flightDTO){
        try {
            System.out.println("blaaa"+flightDTO);
            int result = flightService.SaveFlights(flightDTO);
            ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Flight saved successfully", result);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteFlight(@PathVariable String id) {
        try {
            int result = flightService.DeleteFlight(id);
            ResponseDTO responseDTO = new ResponseDTO(VarList.OK, "Flight deleted successfully", result);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}