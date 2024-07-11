package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String user;
    private LocalDateTime timestamp;
    private String details;
    private String entityName;
    private Long entityId;
    private String ipAddress;
}
