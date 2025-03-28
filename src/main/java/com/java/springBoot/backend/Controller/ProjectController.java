package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Model.Chat;
import com.java.springBoot.backend.Model.Invitation;
import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Request.InviteRequest;
import com.java.springBoot.backend.Response.MessageResponse;
import com.java.springBoot.backend.Service.InvitationService;
import com.java.springBoot.backend.Service.ProjectService;
import com.java.springBoot.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects (
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById (
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject (
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject (
            @PathVariable Long projectId,
            @RequestBody Project updatedProject,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        Project project = projectService.updateProject(updatedProject, projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject (
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse messageResponse = new MessageResponse("Project deleted successfully.");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProject (
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProject(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId (
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject (
            @RequestBody InviteRequest inviteRequest,
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(inviteRequest.getEmail(), inviteRequest.getProjectId());
        MessageResponse messageResponse = new MessageResponse("Invitation sent successfully.");
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation> acceptInvitationProject (
            @RequestParam String token,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }

}
