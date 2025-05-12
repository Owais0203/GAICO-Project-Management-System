package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.StakeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StakeHolderRepository extends JpaRepository<StakeHolder, Long> {

    public List<StakeHolder> findByProjectId(Long projectId);
}
