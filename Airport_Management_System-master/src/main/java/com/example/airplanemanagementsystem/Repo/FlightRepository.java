package com.example.airplanemanagementsystem.Repo;

import com.example.airplanemanagementsystem.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {

}
