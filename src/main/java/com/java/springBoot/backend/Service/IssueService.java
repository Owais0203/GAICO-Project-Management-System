package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Issue;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long id) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, Users user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUsersToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

    List<Issue> getAllIssues() throws Exception;
}
