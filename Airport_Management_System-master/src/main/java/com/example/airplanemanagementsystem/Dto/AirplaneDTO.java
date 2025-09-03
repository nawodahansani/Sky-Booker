package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirplaneDTO {
    private UUID airplaneId;
    private String model;
    private String manufacturer;
    private int capacity;
    private String status;
}
