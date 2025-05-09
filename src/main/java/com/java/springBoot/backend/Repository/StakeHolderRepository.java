package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.StakeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakeHolderRepository extends JpaRepository<StakeHolder, Long> {
}
