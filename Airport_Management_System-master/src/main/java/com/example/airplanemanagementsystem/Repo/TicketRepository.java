package com.example.airplanemanagementsystem.Repo;

import com.example.airplanemanagementsystem.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Tickets,UUID> {
}
