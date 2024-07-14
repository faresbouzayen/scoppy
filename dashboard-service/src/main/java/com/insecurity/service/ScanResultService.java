package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.ScanResult;
import com.insecurity.repository.ScanResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScanResultService {
    @Autowired
    private ScanResultRepository scanResultRepository;

    public Page<ScanResult> getAllScanResults(int page, int size) {
        return scanResultRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<ScanResult> getScanResultById(Long id) {
        return scanResultRepository.findById(id);
    }

    public ScanResult saveScanResult(ScanResult scanResult) {
        scanResult.setScanDate(LocalDateTime.now());
        return scanResultRepository.save(scanResult);
    }

    @Transactional
    public ScanResult updateScanResult(Long id, ScanResult scanResultDetails) {
        ScanResult scanResult = scanResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScanResult not found with id " + id));
        scanResult.setResult(scanResultDetails.getResult());
        scanResult.setStatus(scanResultDetails.getStatus());
        scanResult.setSummary(scanResultDetails.getSummary());
        scanResult.setScanBy(scanResultDetails.getScanBy());
        scanResult.setDetails(scanResultDetails.getDetails());
        return scanResultRepository.save(scanResult);
    }

    public void deleteScanResult(Long id) {
        if (!scanResultRepository.existsById(id)) {
            throw new ResourceNotFoundException("ScanResult not found with id " + id);
        }
        scanResultRepository.deleteById(id);
    }

    public List<ScanResult> getScanResultsByStatus(String status) {
        return scanResultRepository.findByStatus(status);
    }

    public List<ScanResult> getScanResultsByScanConfigId(Long scanConfigId) {
        return scanResultRepository.findByScanConfigId(scanConfigId);
    }

    public List<ScanResult> getScanResultsByScanDate(LocalDateTime scanDate) {
        return scanResultRepository.findByScanDate(scanDate);
    }

    public List<ScanResult> getScanResultsByScanBy(String scanBy) {
        return scanResultRepository.findByScanBy(scanBy);
    }
}
