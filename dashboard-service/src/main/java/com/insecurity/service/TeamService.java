package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.Team;
import com.insecurity.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public Page<Team> getAllTeams(int page, int size) {
        return teamRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team saveTeam(Team team) {
        team.setCreatedDate(LocalDate.now());
        team.setModifiedDate(LocalDate.now());
        return teamRepository.save(team);
    }

    @Transactional
    public Team updateTeam(Long id, Team teamDetails) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id " + id));
        team.setName(teamDetails.getName());
        team.setLead(teamDetails.getLead());
        team.setDepartment(teamDetails.getDepartment());
        team.setDescription(teamDetails.getDescription());
        team.setStatus(teamDetails.getStatus());
        team.setModifiedDate(LocalDate.now());
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team not found with id " + id);
        }
        teamRepository.deleteById(id);
    }

    public List<Team> getTeamsByStatus(String status) {
        return teamRepository.findByStatus(status);
    }

    public List<Team> getTeamsByDepartment(String department) {
        return teamRepository.findByDepartment(department);
    }

    public List<Team> getTeamsByLead(String lead) {
        return teamRepository.findByLead(lead);
    }
}
