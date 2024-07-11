package com.insecurity.repository;

import com.insecurity.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(String recipient);
    List<Notification> findByRecipientAndReadStatus(String recipient, boolean readStatus);
    List<Notification> findByType(String type);
    List<Notification> findByProjectId(Long projectId);

    @Query("SELECT n FROM Notification n WHERE n.recipient = :recipient AND n.type = :type AND n.timestamp BETWEEN :startDate AND :endDate")
    List<Notification> findByRecipientAndTypeAndTimestampBetween(
            @Param("recipient") String recipient,
            @Param("type") String type,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
