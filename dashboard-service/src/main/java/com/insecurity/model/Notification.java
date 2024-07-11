package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String type;
    private boolean readStatus;
    private String recipient;
    private String sender;
    private LocalDateTime timestamp;
    private String link;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
