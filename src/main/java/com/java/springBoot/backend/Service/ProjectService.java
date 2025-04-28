package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Chat;
import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Model.Users;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, Users user) throws Exception;

    List<Project> getProjectByTeam(Users user, String category, String tag) throws Exception;

    Project getProjectById(Long projectId) throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updateProject(Project updatedProject, Long id) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;

    void removeUserFromProject(Long projectId, Long userId) throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project> searchProject(String keyword, Users user) throws Exception;
}
