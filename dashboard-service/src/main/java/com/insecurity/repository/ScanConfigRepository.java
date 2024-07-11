package com.insecurity.repository;

import com.insecurity.model.ScanConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanConfigRepository extends JpaRepository<ScanConfig, Long> {
}
