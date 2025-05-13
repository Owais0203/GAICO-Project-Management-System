package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Model.StakeHolder;
import com.java.springBoot.backend.Repository.StakeHolderRepository;
import com.java.springBoot.backend.Request.StakeholderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StakeHolderServiceImpl implements StakeHolderService{

    @Autowired
    private StakeHolderRepository stakeHolderRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public StakeHolder createStakeHolder(StakeholderRequest stakeholderRequest) throws Exception {

        Project project = projectService.getProjectById(stakeholderRequest.getProjectID());

        StakeHolder stakeHolder = new StakeHolder();
        stakeHolder.setName(stakeholderRequest.getName());
        stakeHolder.setProject(project);
        stakeHolder.setRole(stakeholderRequest.getRole());
        stakeHolder.setContact(stakeholderRequest.getContact());
        stakeHolder.setInfluence(stakeholderRequest.getInfluence());
        stakeHolder.setProjectID(stakeholderRequest.getProjectID());

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

    @Override
    public StakeHolder updateStakeHolder(Long id, StakeHolder stakeHolder) {
        if (stakeHolderRepository.existsById(id)) {
            stakeHolder.setId(id);
            return stakeHolderRepository.save(stakeHolder);
        }
        return null;
    }

    @Override
    public void deleteStakeHolder(Long id) {
        if (stakeHolderRepository.existsById(id)) {
            stakeHolderRepository.deleteById(id);
        }
    }

    @Override
    public List<StakeHolder> getStakeHoldersByProjectId(Long projectId) {
        return stakeHolderRepository.findByProjectId(projectId);
    }

}
