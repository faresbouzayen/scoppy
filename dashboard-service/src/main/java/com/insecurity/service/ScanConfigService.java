package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.ScanConfig;
import com.insecurity.repository.ScanConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScanConfigService {
    @Autowired
    private ScanConfigRepository scanConfigRepository;

    public Page<ScanConfig> getAllScanConfigs(int page, int size) {
        return scanConfigRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<ScanConfig> getScanConfigById(Long id) {
        return scanConfigRepository.findById(id);
    }

    public ScanConfig saveScanConfig(ScanConfig scanConfig) {
        scanConfig.setModifiedDate(LocalDateTime.now());
        return scanConfigRepository.save(scanConfig);
    }

    @Transactional
    public ScanConfig updateScanConfig(Long id, ScanConfig scanConfigDetails) {
        ScanConfig scanConfig = scanConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScanConfig not found with id " + id));
        scanConfig.setName(scanConfigDetails.getName());
        scanConfig.setDescription(scanConfigDetails.getDescription());
        scanConfig.setType(scanConfigDetails.getType());
        scanConfig.setStatus(scanConfigDetails.getStatus());
        scanConfig.setModifiedBy(scanConfigDetails.getModifiedBy());
        scanConfig.setModifiedDate(LocalDateTime.now());
        return scanConfigRepository.save(scanConfig);
    }

    public void deleteScanConfig(Long id) {
        if (!scanConfigRepository.existsById(id)) {
            throw new ResourceNotFoundException("ScanConfig not found with id " + id);
        }
        scanConfigRepository.deleteById(id);
    }

    public List<ScanConfig> getScanConfigsByStatus(String status) {
        return scanConfigRepository.findByStatus(status);
    }

    public List<ScanConfig> getScanConfigsByType(String type) {
        return scanConfigRepository.findByType(type);
    }

    public List<ScanConfig> getScanConfigsModifiedByUser(String modifiedBy) {
        return scanConfigRepository.findByModifiedBy(modifiedBy);
    }

    public List<ScanConfig> getRecentScanConfigs(LocalDateTime fromDate) {
        return scanConfigRepository.findByModifiedDateAfter(fromDate);
    }
}
