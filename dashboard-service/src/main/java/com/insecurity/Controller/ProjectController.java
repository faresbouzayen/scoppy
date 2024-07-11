package com.insecurity.Controller;

import com.insecurity.model.Project;
import com.insecurity.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public Page<Project> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return projectService.getAllProjects(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project updatedProject = projectService.updateProject(id, projectDetails);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner/{owner}")
    public List<Project> getProjectsByOwner(@PathVariable String owner) {
        return projectService.getProjectsByOwner(owner);
    }

    @GetMapping("/status/{status}")
    public List<Project> getProjectsByStatus(@PathVariable String status) {
        return projectService.getProjectsByStatus(status);
    }

    @GetMapping("/category/{category}")
    public List<Project> getProjectsByCategory(@PathVariable String category) {
        return projectService.getProjectsByCategory(category);
    }

    @GetMapping("/date-range")
    public List<Project> getProjectsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return projectService.getProjectsByDateRange(startDate, endDate);
    }

    @GetMapping("/filter")
    public List<Project> filterProjects(
            @RequestParam String owner,
            @RequestParam String status,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String category
    ) {
        return projectService.filterProjects(owner, status, startDate, endDate, category);
    }
}
