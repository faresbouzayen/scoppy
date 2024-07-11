package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.Project;
import com.insecurity.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Page<Project> getAllProjects(int page, int size) {
        return projectRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        project.setName(projectDetails.getName());
        project.setOwner(projectDetails.getOwner());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setStatus(projectDetails.getStatus());
        project.setDescription(projectDetails.getDescription());
        project.setCategory(projectDetails.getCategory());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id " + id);
        }
        projectRepository.deleteById(id);
    }

    public List<Project> getProjectsByOwner(String owner) {
        return projectRepository.findByOwner(owner);
    }

    public List<Project> getProjectsByStatus(String status) {
        return projectRepository.findByStatus(status);
    }

    public List<Project> getProjectsByCategory(String category) {
        return projectRepository.findByCategory(category);
    }

    public List<Project> getProjectsByDateRange(LocalDate startDate, LocalDate endDate) {
        return projectRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<Project> filterProjects(String owner, String status, LocalDate startDate, LocalDate endDate, String category) {
        return projectRepository.findByOwnerAndStatusAndStartDateBetweenAndCategory(owner, status, startDate, endDate, category);
    }
}
