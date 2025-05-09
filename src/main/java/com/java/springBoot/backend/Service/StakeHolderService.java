package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.StakeHolder;

import java.util.List;


public interface StakeHolderService {

    StakeHolder createStakeHolder(StakeHolder stakeHolder);
    List<StakeHolder> getAllStakeHolders();
    StakeHolder getStakeHolderById(Long id);
    StakeHolder updateStakeHolder(Long id, StakeHolder stakeHolder);
    void deleteStakeHolder(Long id);
}
