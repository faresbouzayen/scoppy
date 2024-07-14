package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lead;
    private String department;
    private String description;
    private String status;
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    @OneToMany(mappedBy = "team")
    private List<Project> projects;

}
