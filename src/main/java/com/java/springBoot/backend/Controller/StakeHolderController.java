package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Model.StakeHolder;
import com.java.springBoot.backend.Service.StakeHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public StakeHolder createStakeHolder(@RequestBody StakeHolder stakeHolder) {
        return stakeHolderService.createStakeHolder(stakeHolder);
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
}
