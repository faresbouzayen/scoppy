package com.insecurity.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String owner;
    private String location;
    private String status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vulnerability> vulnerabilities;
}
