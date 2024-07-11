package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.AuditLog;
import com.insecurity.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public Page<AuditLog> getAllAuditLogs(int page, int size) {
        return auditLogRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<AuditLog> getAuditLogById(Long id) {
        return auditLogRepository.findById(id);
    }

    public AuditLog saveAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Transactional
    public AuditLog updateAuditLog(Long id, AuditLog auditLogDetails) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuditLog not found with id " + id));
        auditLog.setAction(auditLogDetails.getAction());
        auditLog.setUser(auditLogDetails.getUser());
        auditLog.setTimestamp(auditLogDetails.getTimestamp());
        auditLog.setDetails(auditLogDetails.getDetails());
        auditLog.setEntityName(auditLogDetails.getEntityName());
        auditLog.setEntityId(auditLogDetails.getEntityId());
        auditLog.setIpAddress(auditLogDetails.getIpAddress());
        return auditLogRepository.save(auditLog);
    }

    public void deleteAuditLog(Long id) {
        if (!auditLogRepository.existsById(id)) {
            throw new ResourceNotFoundException("AuditLog not found with id " + id);
        }
        auditLogRepository.deleteById(id);
    }

    public List<AuditLog> getAuditLogsByUser(String user) {
        return auditLogRepository.findByUser(user);
    }

    public List<AuditLog> getAuditLogsByAction(String action) {
        return auditLogRepository.findByAction(action);
    }

    public List<AuditLog> getAuditLogsByEntity(String entityName, Long entityId) {
        return auditLogRepository.findByEntityNameAndEntityId(entityName, entityId);
    }

    public List<AuditLog> getAuditLogsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return auditLogRepository.findByTimestampBetween(startDate, endDate);
    }

    public List<AuditLog> filterAuditLogs(String action, String user, String entityName, LocalDateTime startDate, LocalDateTime endDate) {
        return auditLogRepository.findByActionAndUserAndEntityNameAndTimestampBetween(action, user, entityName, startDate, endDate);
    }
}
