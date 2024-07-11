package com.insecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private LocalDate date;
    private String type;
    private String status;
    private String content;
    private String summary;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
