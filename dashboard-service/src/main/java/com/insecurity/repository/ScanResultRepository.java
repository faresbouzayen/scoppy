package com.insecurity.repository;

import com.insecurity.model.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ScanResultRepository extends JpaRepository<ScanResult, Long> {
    List<ScanResult> findByStatus(String status);
    List<ScanResult> findByScanConfigId(Long scanConfigId);
    List<ScanResult> findByScanDate(LocalDateTime scanDate);
    List<ScanResult> findByScanBy(String scanBy);
}
