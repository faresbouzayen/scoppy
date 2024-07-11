package com.insecurity.repository;

import com.insecurity.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByProjectId(Long projectId);
    List<Asset> findByStatus(String status);
    List<Asset> findByOwner(String owner);

    @Query("SELECT a FROM Asset a WHERE a.status = :status AND a.type = :type AND a.owner = :owner")
    List<Asset> findByStatusAndTypeAndOwner(@Param("status") String status, @Param("type") String type, @Param("owner") String owner);

    @Query("SELECT a FROM Asset a WHERE SIZE(a.vulnerabilities) > :minVulnerabilities")
    List<Asset> findByVulnerabilityCountGreaterThan(@Param("minVulnerabilities") int minVulnerabilities);

    long countByStatus(String status);
}
