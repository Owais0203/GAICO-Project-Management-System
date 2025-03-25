package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // List<Project> findByOwner(Users user);

    List<Project> findByNameContainingAndTeamContains(String partialName, Users user);

//    @Query("SELECT p FROM Project p join p.team t where t = :user")
//    List<Project> findProjectByTeam(@Param("user") Users user);

    List<Project> findByTeamContainingOrOwner(Users user, Users owner);
}
