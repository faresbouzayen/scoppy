package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.Report;
import com.insecurity.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Page<Report> getAllReports(int page, int size) {
        return reportRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Transactional
    public Report updateReport(Long id, Report reportDetails) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id " + id));
        report.setTitle(reportDetails.getTitle());
        report.setAuthor(reportDetails.getAuthor());
        report.setDate(reportDetails.getDate());
        report.setType(reportDetails.getType());
        report.setStatus(reportDetails.getStatus());
        report.setContent(reportDetails.getContent());
        report.setSummary(reportDetails.getSummary());
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Report not found with id " + id);
        }
        reportRepository.deleteById(id);
    }

    public List<Report> getReportsByAuthor(String author) {
        return reportRepository.findByAuthor(author);
    }

    public List<Report> getReportsByStatus(String status) {
        return reportRepository.findByStatus(status);
    }

    public List<Report> getReportsByType(String type) {
        return reportRepository.findByType(type);
    }

    public List<Report> getReportsByDateRange(LocalDate startDate, LocalDate endDate) {
        return reportRepository.findByDateBetween(startDate, endDate);
    }

    public List<Report> filterReports(String author, String status, LocalDate startDate, LocalDate endDate, String type) {
        return reportRepository.findByAuthorAndStatusAndDateBetweenAndType(author, status, startDate, endDate, type);
    }
}
