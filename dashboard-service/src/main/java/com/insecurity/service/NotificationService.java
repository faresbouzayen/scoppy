package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.Notification;
import com.insecurity.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Page<Notification> getAllNotifications(int page, int size) {
        return notificationRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification updateNotification(Long id, Notification notificationDetails) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id " + id));
        notification.setMessage(notificationDetails.getMessage());
        notification.setType(notificationDetails.getType());
        notification.setReadStatus(notificationDetails.isReadStatus());
        notification.setRecipient(notificationDetails.getRecipient());
        notification.setSender(notificationDetails.getSender());
        notification.setLink(notificationDetails.getLink());
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notification not found with id " + id);
        }
        notificationRepository.deleteById(id);
    }

    public List<Notification> getNotificationsByRecipient(String recipient) {
        return notificationRepository.findByRecipient(recipient);
    }

    public List<Notification> getUnreadNotifications(String recipient) {
        return notificationRepository.findByRecipientAndReadStatus(recipient, false);
    }

    public List<Notification> getNotificationsByType(String type) {
        return notificationRepository.findByType(type);
    }

    public List<Notification> getNotificationsByProject(Long projectId) {
        return notificationRepository.findByProjectId(projectId);
    }

    public List<Notification> filterNotifications(String recipient, String type, LocalDateTime startDate, LocalDateTime endDate) {
        return notificationRepository.findByRecipientAndTypeAndTimestampBetween(recipient, type, startDate, endDate);
    }

    public void markNotificationsAsRead(List<Long> notificationIds) {
        List<Notification> notifications = notificationRepository.findAllById(notificationIds);
        notifications.forEach(notification -> notification.setReadStatus(true));
        notificationRepository.saveAll(notifications);
    }
}
