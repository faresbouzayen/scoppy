package com.insecurity.repository;

import com.insecurity.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByAuthor(String author);
    List<Report> findByStatus(String status);
    List<Report> findByType(String type);
    List<Report> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Report r WHERE r.author = :author AND r.status = :status AND r.date BETWEEN :startDate AND :endDate AND r.type = :type")
    List<Report> findByAuthorAndStatusAndDateBetweenAndType(
            @Param("author") String author,
            @Param("status") String status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("type") String type
    );
}
