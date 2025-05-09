package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {
    private String name;
    private int ID;
    private List<ActivityInfo> activities = new ArrayList<>();
    private String projectLeaderUsername;

    public ProjectInfo(Project project) {
        this.name = project.getName();
        this.ID = project.getID();
        
        this.projectLeaderUsername = project.getProjectLeaderName();
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
