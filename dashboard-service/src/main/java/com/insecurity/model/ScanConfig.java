package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class ScanConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String type;
    private String status;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "scanConfig", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ScanResult> scanResults;
}
