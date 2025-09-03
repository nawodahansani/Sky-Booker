package com.example.airplanemanagementsystem.Controller;

import jakarta.validation.Valid;
import com.example.airplanemanagementsystem.Dto.AuthDTO;
import com.example.airplanemanagementsystem.Dto.ResponseDTO;
import com.example.airplanemanagementsystem.Dto.UserDTO;
import com.example.airplanemanagementsystem.Service.UserService;
import com.example.airplanemanagementsystem.Util.JwtUtil;
import com.example.airplanemanagementsystem.Util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    //constructor injection
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            List<UserDTO> userDTOS = userService.getAllUsers();
            ResponseDTO responseDTO = new ResponseDTO(VarList.Created, "Success", userDTOS);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.Bad_Gateway, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String email) {
        try {
            int result = userService.deleteUser(email);
            if (result == VarList.Deleted) {
                ResponseDTO responseDTO = new ResponseDTO(VarList.RSP_SUCCESS, "User deleted successfully", null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else if (result == VarList.Not_Found) {
                ResponseDTO responseDTO = new ResponseDTO(VarList.RSP_NO_DATA_FOUND, "User not found", null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else {
                ResponseDTO responseDTO = new ResponseDTO(VarList.RSP_ERROR, "Error deleting user", null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(VarList.RSP_ERROR, e.getMessage(), null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
