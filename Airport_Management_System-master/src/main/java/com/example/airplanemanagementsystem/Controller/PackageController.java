package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.AirplaneDTO;
import com.example.airplanemanagementsystem.Dto.PackageDTO;
import com.example.airplanemanagementsystem.Dto.ResponseDTO;
import com.example.airplanemanagementsystem.Service.PackageService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/package")
@CrossOrigin("*")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("save")
    public ResponseEntity<ResponseDTO> savePackage(@RequestBody @Valid PackageDTO packageDTO){
        try {
            System.out.println("blaaa"+packageDTO);
            int result = packageService.SavePackage(packageDTO);
            ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Package saved successfully", result);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllPackages(){
        try {
            return new ResponseEntity<>(new ResponseDTO(VarList.OK, "All packages", packageService.getAllPackages()), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updatePackage(@RequestBody @Valid PackageDTO packageDTO) {
        try {
            int result = packageService.UpdatePackage(packageDTO);
            if (result == VarList.OK) {
                return new ResponseEntity<>(new ResponseDTO(VarList.OK, "Package updated successfully", result), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO(VarList.Not_Found, "Package not found", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deletePackage(@PathVariable UUID id) {
        try {
            int result = packageService.DeletePackage(id);
            if (result == VarList.Deleted) {
                return new ResponseEntity<>(new ResponseDTO(VarList.Deleted, "Package deleted successfully", result), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO(VarList.Not_Found, "Package not found", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
