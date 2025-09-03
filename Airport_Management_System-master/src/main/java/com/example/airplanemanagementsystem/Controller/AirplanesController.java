package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.AirplaneDTO;
import com.example.airplanemanagementsystem.Dto.ResponseDTO;
import com.example.airplanemanagementsystem.Service.AirplaneService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/plane")
@CrossOrigin("*")
public class AirplanesController {
    private final AirplaneService airplaneService;

    public AirplanesController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }
    @PostMapping("save")
    public ResponseEntity<ResponseDTO>saveAirplane(@Valid @RequestBody AirplaneDTO airplaneDTO){
        try {
            System.out.println("blaaa"+airplaneDTO);
            int result = airplaneService.SaveFlights(airplaneDTO);
            ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Airplane saved successfully", result);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
