package com.example.airplanemanagementsystem.Service;

import com.example.airplanemanagementsystem.Dto.FlightDTO;
import com.example.airplanemanagementsystem.Dto.PackageDTO;

import java.util.List;
import java.util.UUID;

public interface PackageService {
    int SavePackage(PackageDTO packageDTO);
    List<PackageDTO> getAllPackages();
    int DeletePackage(UUID Id);
    int UpdatePackage(PackageDTO packageDTO);
    int SearchPackage(UUID packageId);

}
