package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Issue;
import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Repository.IssueRepository;
import com.java.springBoot.backend.Request.IssueRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;
    private Logger logger;

    @Override
    public Issue getIssueById(Long id) throws Exception {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new Exception("No issue found with issue id: " + id);
        }
    }

    @Override
    public List<Issue> getIssuesByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, Users user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getProjectId());

        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setProjectID(issueRequest.getProjectId());
        issue.setPriority(issueRequest.getPriority());
        issue.setEndDate(issueRequest.getEndDate());
        issue.setStartDate(issueRequest.getStartDate());

        issue.setProject(project);

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {

        getIssueById(issueId);

        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUsersToIssue(Long issueId, Long userId) throws Exception {
        Users user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);

        issue.setAssignee(user);

        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {

        Issue issue = getIssueById(issueId);
        issue.setStatus(status);

        return issueRepository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() throws Exception {
        try {
            // Fetch all issues from repository
            List<Issue> issues = issueRepository.findAll();

            // Optional: Sort by creation date (newest first)
            issues.sort(Comparator.comparing(Issue::getStartDate).reversed());

            return issues;
        } catch (Exception ex) {
            logger.error("Error fetching all issues", ex);
            throw new Exception("Failed to retrieve all issues", ex);
        }
    }
}
