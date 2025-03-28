package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.DTO.IssueDTO;
import com.java.springBoot.backend.Model.Issue;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Request.IssueRequest;
import com.java.springBoot.backend.Response.AuthResponse;
import com.java.springBoot.backend.Service.IssueService;
import com.java.springBoot.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssuesByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue, @RequestHeader("Authorization") String token) throws Exception {
        Users tokenUser = userService.findUserProfileByJwt(token);
        Users user = userService.findUserById(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issue, tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectId(createdIssue.getProject().getId());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setAssignee(createdIssue.getAssignee());

        return ResponseEntity.ok(issueDTO);

    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<AuthResponse> deleteIssue(@PathVariable Long issueId, @RequestHeader("Authorization") String token) throws Exception {
        Users user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());

        AuthResponse response = new AuthResponse();
        response.setMessage("Issue deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUsersToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }

}
