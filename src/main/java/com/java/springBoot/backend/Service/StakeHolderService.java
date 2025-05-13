package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.StakeHolder;
import com.java.springBoot.backend.Request.StakeholderRequest;

import java.util.List;


public interface StakeHolderService {

    StakeHolder createStakeHolder(StakeholderRequest stakeholderRequest) throws Exception;
    List<StakeHolder> getAllStakeHolders();
    StakeHolder getStakeHolderById(Long id);
    StakeHolder updateStakeHolder(Long id, StakeHolder stakeHolder);
    void deleteStakeHolder(Long id);
    List<StakeHolder> getStakeHoldersByProjectId(Long projectId);
}
