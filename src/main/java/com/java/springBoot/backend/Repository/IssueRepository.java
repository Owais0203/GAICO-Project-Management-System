package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    public List<Issue> findByProjectId(Long projectId);
}
