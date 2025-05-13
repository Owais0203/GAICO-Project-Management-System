package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.DTO.ActivityDTO;
import com.java.springBoot.backend.Model.Activity;
import com.java.springBoot.backend.Request.ActivityRequest;
import com.java.springBoot.backend.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // Create Activity
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityRequest activityRequest) throws Exception {
        Activity createdActivity = activityService.createActivity(activityRequest);

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(createdActivity.getId());
        activityDTO.setName(createdActivity.getName());
        activityDTO.setDuration(createdActivity.getDuration());
        activityDTO.setCost(createdActivity.getCost());
        activityDTO.setBudget(createdActivity.getBudget());
        activityDTO.setProjectID(createdActivity.getProjectID());
        activityDTO.setProject(createdActivity.getProject());

        return ResponseEntity.ok(activityDTO);
    }

    // Get all activities
    @GetMapping("/all")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    // Get Activity by ID
    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    // Update charter
    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return activityService.updateActivity(id, activity);
    }

    // Delete Activity
    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }

    // Get Activity by project ID
    @GetMapping("/project/{projectId}")
    public Activity getActivitiesByProjectId(@PathVariable Long projectId) {
        return activityService.getActivitiesByProjectId(projectId);
    }

}
