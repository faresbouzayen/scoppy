package com.insecurity.repository;

import com.insecurity.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUser(String user);
    List<AuditLog> findByAction(String action);
    List<AuditLog> findByEntityNameAndEntityId(String entityName, Long entityId);

    @Query("SELECT a FROM AuditLog a WHERE a.timestamp BETWEEN :startDate AND :endDate")
    List<AuditLog> findByTimestampBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT a FROM AuditLog a WHERE a.action = :action AND a.user = :user AND a.entityName = :entityName AND a.timestamp BETWEEN :startDate AND :endDate")
    List<AuditLog> findByActionAndUserAndEntityNameAndTimestampBetween(
            @Param("action") String action,
            @Param("user") String user,
            @Param("entityName") String entityName,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
