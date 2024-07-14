package com.insecurity.Controller;

import com.insecurity.model.Report;
import com.insecurity.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public Page<Report> getAllReports(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return reportService.getAllReports(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        Report updatedReport = reportService.updateReport(id, reportDetails);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{author}")
    public List<Report> getReportsByAuthor(@PathVariable String author) {
        return reportService.getReportsByAuthor(author);
    }

    @GetMapping("/status/{status}")
    public List<Report> getReportsByStatus(@PathVariable String status) {
        return reportService.getReportsByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<Report> getReportsByType(@PathVariable String type) {
        return reportService.getReportsByType(type);
    }

    @GetMapping("/date-range")
    public List<Report> getReportsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return reportService.getReportsByDateRange(startDate, endDate);
    }

    @GetMapping("/filter")
    public List<Report> filterReports(
            @RequestParam String author,
            @RequestParam String status,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String type
    ) {
        return reportService.filterReports(author, status, startDate, endDate, type);
    }
}
