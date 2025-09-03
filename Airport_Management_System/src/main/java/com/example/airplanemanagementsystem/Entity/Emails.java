package com.example.airplanemanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String message;
    private LocalDateTime sentDate;
}
