package com.example.airplanemanagementsystem.Service;


import com.example.airplanemanagementsystem.Dto.UserDTO;

import java.util.List;


public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    List<UserDTO> getAllUsers();
    int deleteUser(String email);
}