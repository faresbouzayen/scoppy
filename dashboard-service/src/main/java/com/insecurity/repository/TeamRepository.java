package com.insecurity.repository;

import com.insecurity.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByStatus(String status);
    List<Team> findByDepartment(String department);
    List<Team> findByLead(String lead);
}
