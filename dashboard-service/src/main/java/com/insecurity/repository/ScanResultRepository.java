package com.insecurity.repository;

import com.insecurity.model.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanResultRepository extends JpaRepository<ScanResult, Long> {
}
