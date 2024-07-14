package com.insecurity.Controller;

import com.insecurity.model.ScanConfig;
import com.insecurity.service.ScanConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scan-configs")
public class ScanConfigController {
    @Autowired
    private ScanConfigService scanConfigService;

    @GetMapping
    public Page<ScanConfig> getAllScanConfigs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return scanConfigService.getAllScanConfigs(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScanConfig> getScanConfigById(@PathVariable Long id) {
        Optional<ScanConfig> scanConfig = scanConfigService.getScanConfigById(id);
        return scanConfig.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ScanConfig createScanConfig(@RequestBody ScanConfig scanConfig) {
        return scanConfigService.saveScanConfig(scanConfig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScanConfig> updateScanConfig(@PathVariable Long id, @RequestBody ScanConfig scanConfigDetails) {
        ScanConfig updatedScanConfig = scanConfigService.updateScanConfig(id, scanConfigDetails);
        return ResponseEntity.ok(updatedScanConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScanConfig(@PathVariable Long id) {
        scanConfigService.deleteScanConfig(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<ScanConfig> getScanConfigsByStatus(@PathVariable String status) {
        return scanConfigService.getScanConfigsByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<ScanConfig> getScanConfigsByType(@PathVariable String type) {
        return scanConfigService.getScanConfigsByType(type);
    }

    @GetMapping("/modified-by/{modifiedBy}")
    public List<ScanConfig> getScanConfigsModifiedByUser(@PathVariable String modifiedBy) {
        return scanConfigService.getScanConfigsModifiedByUser(modifiedBy);
    }

    @GetMapping("/recent")
    public List<ScanConfig> getRecentScanConfigs(@RequestParam LocalDateTime fromDate) {
        return scanConfigService.getRecentScanConfigs(fromDate);
    }
}
