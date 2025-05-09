package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.StakeHolder;
import com.java.springBoot.backend.Repository.StakeHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StakeHolderServiceImpl implements StakeHolderService{

    private final StakeHolderRepository stakeHolderRepository;

    @Autowired
    public StakeHolderServiceImpl(StakeHolderRepository stakeHolderRepository) {
        this.stakeHolderRepository = stakeHolderRepository;
    }

    @Override
    public StakeHolder createStakeHolder(StakeHolder stakeHolder) {
        return stakeHolderRepository.save(stakeHolder);
    }

    @Override
    public List<StakeHolder> getAllStakeHolders() {
        return stakeHolderRepository.findAll();
    }

    @Override
    public StakeHolder getStakeHolderById(Long id) {
        return stakeHolderRepository.findById(id).orElse(null);
    }

}
