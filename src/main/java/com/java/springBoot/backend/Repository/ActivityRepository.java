package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    public Activity findByProjectId(Long projectId);
}
