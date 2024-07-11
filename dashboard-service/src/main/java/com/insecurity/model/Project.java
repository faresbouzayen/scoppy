package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String owner;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String description;
    private String category;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Asset> assets;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Notification> notifications;
}
