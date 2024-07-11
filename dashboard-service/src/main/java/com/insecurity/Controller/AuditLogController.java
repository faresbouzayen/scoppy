package com.insecurity.Controller;

import com.insecurity.model.AuditLog;
import com.insecurity.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {
    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public Page<AuditLog> getAllAuditLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return auditLogService.getAllAuditLogs(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable Long id) {
        Optional<AuditLog> auditLog = auditLogService.getAuditLogById(id);
        return auditLog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AuditLog createAuditLog(@RequestBody AuditLog auditLog) {
        return auditLogService.saveAuditLog(auditLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLog> updateAuditLog(@PathVariable Long id, @RequestBody AuditLog auditLogDetails) {
        AuditLog updatedAuditLog = auditLogService.updateAuditLog(id, auditLogDetails);
        return ResponseEntity.ok(updatedAuditLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{user}")
    public List<AuditLog> getAuditLogsByUser(@PathVariable String user) {
        return auditLogService.getAuditLogsByUser(user);
    }

    @GetMapping("/action/{action}")
    public List<AuditLog> getAuditLogsByAction(@PathVariable String action) {
        return auditLogService.getAuditLogsByAction(action);
    }

    @GetMapping("/entity")
    public List<AuditLog> getAuditLogsByEntity(
            @RequestParam String entityName,
            @RequestParam Long entityId
    ) {
        return auditLogService.getAuditLogsByEntity(entityName, entityId);
    }

    @GetMapping("/date-range")
    public List<AuditLog> getAuditLogsBetweenDates(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate
    ) {
        return auditLogService.getAuditLogsBetweenDates(startDate, endDate);
    }

    @GetMapping("/filter")
    public List<AuditLog> filterAuditLogs(
            @RequestParam String action,
            @RequestParam String user,
            @RequestParam String entityName,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate
    ) {
        return auditLogService.filterAuditLogs(action, user, entityName, startDate, endDate);
    }
}
