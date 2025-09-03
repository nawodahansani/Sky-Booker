package com.example.airplanemanagementsystem.Service;

import com.example.airplanemanagementsystem.Dto.TicketDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    int saveTickets(TicketDTO ticketDTO);
    List<TicketDTO> getAllTickets();
    int deleteTicket(UUID id);
    int updateTicket(TicketDTO ticketDTO);

}
