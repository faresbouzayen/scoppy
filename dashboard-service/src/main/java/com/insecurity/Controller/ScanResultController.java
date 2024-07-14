package com.insecurity.Controller;

import com.insecurity.model.ScanResult;
import com.insecurity.service.ScanResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scan-results")
public class ScanResultController {
    @Autowired
    private ScanResultService scanResultService;

    @GetMapping
    public Page<ScanResult> getAllScanResults(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return scanResultService.getAllScanResults(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScanResult> getScanResultById(@PathVariable Long id) {
        Optional<ScanResult> scanResult = scanResultService.getScanResultById(id);
        return scanResult.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ScanResult createScanResult(@RequestBody ScanResult scanResult) {
        return scanResultService.saveScanResult(scanResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScanResult> updateScanResult(@PathVariable Long id, @RequestBody ScanResult scanResultDetails) {
        ScanResult updatedScanResult = scanResultService.updateScanResult(id, scanResultDetails);
        return ResponseEntity.ok(updatedScanResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScanResult(@PathVariable Long id) {
        scanResultService.deleteScanResult(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<ScanResult> getScanResultsByStatus(@PathVariable String status) {
        return scanResultService.getScanResultsByStatus(status);
    }

    @GetMapping("/scan-config/{scanConfigId}")
    public List<ScanResult> getScanResultsByScanConfigId(@PathVariable Long scanConfigId) {
        return scanResultService.getScanResultsByScanConfigId(scanConfigId);
    }

    @GetMapping("/scan-date")
    public List<ScanResult> getScanResultsByScanDate(@RequestParam LocalDateTime scanDate) {
        return scanResultService.getScanResultsByScanDate(scanDate);
    }

    @GetMapping("/scan-by/{scanBy}")
    public List<ScanResult> getScanResultsByScanBy(@PathVariable String scanBy) {
        return scanResultService.getScanResultsByScanBy(scanBy);
    }
}
