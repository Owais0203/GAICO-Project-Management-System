package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Activity;
import com.java.springBoot.backend.Request.ActivityRequest;

import java.util.List;

public interface ActivityService {

    // Method to create an activity
    Activity createActivity(ActivityRequest activityRequest) throws Exception;

    // Method to get all activities
    List<Activity> getAllActivities();

    // Method to get a activity by ID
    Activity getActivityById(Long id);

    // Method to update an activity
    Activity updateActivity(Long id, Activity activity);

    // Method to delete an activity
    void deleteActivity(Long id);

    // Method to get Activities by project ID
    Activity getActivitiesByProjectId(Long projectId);

}
