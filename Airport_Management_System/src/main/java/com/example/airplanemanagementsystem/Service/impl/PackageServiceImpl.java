package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Dto.PackageDTO;
import com.example.airplanemanagementsystem.Entity.Packages;
import com.example.airplanemanagementsystem.Repo.PackageRepository;
import com.example.airplanemanagementsystem.Service.PackageService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public int SavePackage(PackageDTO packageDTO) {
        packageRepository.save(modelMapper.map(packageDTO, Packages.class));
        return VarList.Created;
    }

    @Override
    public List<PackageDTO> getAllPackages() {
        List<Packages> packagesList = packageRepository.findAll();
        return packagesList.stream()
                .map(flight -> modelMapper.map(flight, PackageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int DeletePackage(UUID code) {
        if (packageRepository.existsById(code)) {
            packageRepository.deleteById(code);
            return VarList.Deleted;
        } else {
            return VarList.Not_Found;
        }
    }



    @Override
    public int UpdatePackage(PackageDTO packageDTO) {
            packageRepository.save(modelMapper.map(packageDTO, Packages.class));
            return VarList.OK;
    }

    @Override
    public int SearchPackage(UUID packageId) {
        Optional<Packages> packages = packageRepository.findById(packageId);
        return packages.isPresent() ? VarList.Found : VarList.Not_Found;
    }
}
