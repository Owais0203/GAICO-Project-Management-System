package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.DTO.IssueDTO;
import com.java.springBoot.backend.DTO.StakeholderDTO;
import com.java.springBoot.backend.Model.StakeHolder;
import com.java.springBoot.backend.Request.StakeholderRequest;
import com.java.springBoot.backend.Service.StakeHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stakeholders")
public class StakeHolderController {

    @Autowired
    private final StakeHolderService stakeHolderService;

    @Autowired
    public StakeHolderController(StakeHolderService stakeHolderService) {
        this.stakeHolderService = stakeHolderService;
    }

    // Create stakeholder
    @PostMapping
    public ResponseEntity<StakeholderDTO> createStakeHolder(@RequestBody StakeholderRequest stakeHolder) throws Exception {

        StakeHolder createdStakeHolder = stakeHolderService.createStakeHolder(stakeHolder);
        StakeholderDTO stakeholderDTO = new StakeholderDTO();
        stakeholderDTO.setId(createdStakeHolder.getId());
        stakeholderDTO.setName(createdStakeHolder.getName());
        stakeholderDTO.setContact(createdStakeHolder.getContact());
        stakeholderDTO.setInfluence(createdStakeHolder.getInfluence());
        stakeholderDTO.setRole(createdStakeHolder.getRole());
        stakeholderDTO.setProjectID(createdStakeHolder.getProjectID());
        stakeholderDTO.setProject(createdStakeHolder.getProject());

        return ResponseEntity.ok(stakeholderDTO);
    }

    // Get all stakeholders
    @GetMapping
    public List<StakeHolder> getAllStakeHolders() {
        return stakeHolderService.getAllStakeHolders();
    }

    // Get stakeholder by ID
    @GetMapping("/{id}")
    public StakeHolder getStakeHolderById(@PathVariable Long id) {
        return stakeHolderService.getStakeHolderById(id);
    }

    // Update stakeholder
    @PutMapping("/{id}")
    public StakeHolder updateStakeHolder(@PathVariable Long id, @RequestBody StakeHolder stakeHolder) {
        return stakeHolderService.updateStakeHolder(id, stakeHolder);
    }

    // Delete stakeholder
    @DeleteMapping("/{id}")
    public void deleteStakeHolder(@PathVariable Long id) {
        stakeHolderService.deleteStakeHolder(id);
    }

    // Get stakeholder By ProjectId
    @GetMapping("/project/{projectId}")
    public List<StakeHolder> getStakeHoldersByProjectId(@PathVariable Long projectId) {
        return stakeHolderService.getStakeHoldersByProjectId(projectId);
    }
}
