package com.insecurity.repository;

import com.insecurity.model.ScanConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ScanConfigRepository extends JpaRepository<ScanConfig, Long> {
    List<ScanConfig> findByStatus(String status);
    List<ScanConfig> findByType(String type);
    List<ScanConfig> findByModifiedBy(String modifiedBy);
    List<ScanConfig> findByModifiedDateAfter(LocalDateTime fromDate);
}
