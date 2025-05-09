package dtu.example.DTO;

import java.util.ArrayList;
import java.util.List;

import dtu.example.Project;
import io.cucumber.java.cs.A;

public class ProjectInfo {
    private String name;
    private int ID;
    private List<ActivityInfo> activities = new ArrayList<>();
    private String projectLeaderUsername;

    public ProjectInfo(Project project) {
        this.name = project.getName();
        this.ID = project.getID();
        this.activities = new ArrayList<>();
        for (int i = 0; i < project.getActivities().size(); i++) {
            activities.add(new ActivityInfo(project.getActivities().get(i)));
        }
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
