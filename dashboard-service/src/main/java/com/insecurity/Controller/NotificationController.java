package com.insecurity.Controller;

import com.insecurity.model.Notification;
import com.insecurity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public Page<Notification> getAllNotifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return notificationService.getAllNotifications(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Notification updatedNotification = notificationService.updateNotification(id, notificationDetails);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recipient/{recipient}")
    public List<Notification> getNotificationsByRecipient(@PathVariable String recipient) {
        return notificationService.getNotificationsByRecipient(recipient);
    }

    @GetMapping("/unread/{recipient}")
    public List<Notification> getUnreadNotifications(@PathVariable String recipient) {
        return notificationService.getUnreadNotifications(recipient);
    }

    @GetMapping("/type/{type}")
    public List<Notification> getNotificationsByType(@PathVariable String type) {
        return notificationService.getNotificationsByType(type);
    }

    @GetMapping("/project/{projectId}")
    public List<Notification> getNotificationsByProject(@PathVariable Long projectId) {
        return notificationService.getNotificationsByProject(projectId);
    }

    @GetMapping("/filter")
    public List<Notification> filterNotifications(
            @RequestParam String recipient,
            @RequestParam String type,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate
    ) {
        return notificationService.filterNotifications(recipient, type, startDate, endDate);
    }

    @PostMapping("/mark-read")
    public ResponseEntity<Void> markNotificationsAsRead(@RequestBody List<Long> notificationIds) {
        notificationService.markNotificationsAsRead(notificationIds);
        return ResponseEntity.noContent().build();
    }
}
