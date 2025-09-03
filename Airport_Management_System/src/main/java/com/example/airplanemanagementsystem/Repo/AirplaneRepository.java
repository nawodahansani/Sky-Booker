package com.example.airplanemanagementsystem.Repo;

import com.example.airplanemanagementsystem.Entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirplaneRepository extends JpaRepository<Airplane,Integer> {

    boolean existsById(UUID airplaneId);

    void deleteById(UUID airplaneId);
}
