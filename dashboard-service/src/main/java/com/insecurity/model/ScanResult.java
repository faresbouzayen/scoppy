package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ScanResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;
    private String status;
    private String summary;
    private LocalDateTime scanDate;
    private String scanBy;
    private String details;

    @ManyToOne
    @JoinColumn(name = "scan_config_id")
    private ScanConfig scanConfig;
}
