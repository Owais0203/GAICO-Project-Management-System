package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Charter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharterRepository extends JpaRepository<Charter, Long> {

    public Charter findByProjectId(Long projectId);
}
