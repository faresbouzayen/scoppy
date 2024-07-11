package com.insecurity.repository;

import com.insecurity.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(String owner);
    List<Project> findByStatus(String status);
    List<Project> findByCategory(String category);
    List<Project> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Project p WHERE p.owner = :owner AND p.status = :status AND p.startDate BETWEEN :startDate AND :endDate AND p.category = :category")
    List<Project> findByOwnerAndStatusAndStartDateBetweenAndCategory(
            @Param("owner") String owner,
            @Param("status") String status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("category") String category
    );
}
