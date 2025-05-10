package dtu.example.dto;

import java.util.ArrayList;
import java.util.List;

import dtu.example.Model.Activity;
import dtu.example.Model.Project;

public class ProjectInfo {
    private String name;
    private int ID;
    private List<ActivityInfo> activities = new ArrayList<>();
    private String projectLeaderUsername;

    public ProjectInfo(Project project) {
        this.name = project.getName();
        this.ID = project.getID();
        this.activities = new ArrayList<>();
        for (Activity activity : project.getActivities()) {
            activities.add(new ActivityInfo(activity));
        }
        try {
            this.projectLeaderUsername = project.getProjectLeaderName();
        } catch (Exception e) {
            this.projectLeaderUsername = "";
        }
        
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public List<ActivityInfo> getActivities() {
        return activities;
    }

    public String getProjectLeaderUsername() {
        return projectLeaderUsername;
    }
}
