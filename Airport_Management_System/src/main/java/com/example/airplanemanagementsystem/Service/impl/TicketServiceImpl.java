package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Dto.TicketDTO;
import com.example.airplanemanagementsystem.Dto.TicketDTO;
import com.example.airplanemanagementsystem.Entity.Tickets;
import com.example.airplanemanagementsystem.Entity.Tickets;
import com.example.airplanemanagementsystem.Repo.TicketRepository;
import com.example.airplanemanagementsystem.Service.TicketService;
import com.example.airplanemanagementsystem.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveTickets(TicketDTO ticketDTO) {
        ticketRepository.save(modelMapper.map(ticketDTO, Tickets.class));
        return VarList.Created;

    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Tickets> airplaneList = ticketRepository.findAll();
        return airplaneList.stream()
                .map(airplane -> modelMapper.map(airplane, TicketDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public int deleteTicket(UUID id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return VarList.Deleted;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateTicket(TicketDTO ticketDTO) {
        if (ticketRepository.existsById(ticketDTO.getTicketId())) {
            ticketRepository.save(modelMapper.map(ticketDTO, Tickets.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }



}
