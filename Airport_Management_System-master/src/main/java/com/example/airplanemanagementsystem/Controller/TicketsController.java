package com.example.airplanemanagementsystem.Controller;

import com.example.airplanemanagementsystem.Dto.ResponseDTO;
import com.example.airplanemanagementsystem.Entity.Tickets;
import com.example.airplanemanagementsystem.Service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket")
@CrossOrigin("*")
public class TicketsController {
    private final TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;

    }

}
