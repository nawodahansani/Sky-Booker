package com.example.airplanemanagementsystem.Repo;

import com.example.airplanemanagementsystem.Entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface PackageRepository extends JpaRepository<Packages, UUID> {

    Optional<Packages> findById(UUID packageId);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
