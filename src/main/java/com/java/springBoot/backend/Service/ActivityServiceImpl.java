package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Activity;
import com.java.springBoot.backend.Model.Project;
import com.java.springBoot.backend.Repository.ActivityRepository;
import com.java.springBoot.backend.Request.ActivityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements  ActivityService{

    @Autowired
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Autowired
    private ProjectService projectService;


    @Override
    public Activity createActivity(ActivityRequest activityRequest) throws Exception {

        Project project = projectService.getProjectById(activityRequest.getProjectID());

        Activity newActivity = new Activity();
        newActivity.setName(activityRequest.getName());
        newActivity.setDuration(activityRequest.getDuration());
        newActivity.setCost(activityRequest.getCost());
        newActivity.setBudget(activityRequest.getBudget());
        newActivity.setProject(project);
        newActivity.setProjectID(activityRequest.getProjectID());

        return activityRepository.save(newActivity);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public Activity updateActivity(Long id, Activity activity) {
        if (activityRepository.existsById(id)) {
            activity.setId(activity.getId());
            activity.setBudget(activity.getBudget());
            activity.setDuration(activity.getDuration());
            activity.setCost(activity.getCost());
            activity.setProject(activity.getProject());
            activity.setName(activity.getName());
            activity.setProjectID(activity.getProjectID());

            return activityRepository.save(activity);
        }
        return null;
    }

    @Override
    public void deleteActivity(Long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
        }
    }

    @Override
    public Activity getActivitiesByProjectId(Long projectId) {
        return activityRepository.findByProjectId(projectId);
    }
}
