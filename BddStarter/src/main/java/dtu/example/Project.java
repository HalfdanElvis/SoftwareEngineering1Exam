package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int ID;
    private List<Activity> activities = new ArrayList<>();
    private Employee projectLeader;

    public Project (String name){
        this.name = name;
        activities = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public boolean hasProjectLeader() {
        if (projectLeader == null) {
            return false;
        }
        return true;
    }

    public String printProject() {
        return name+", "+ID;
    }

    public void createActivity(String name) {
        activities.add(new Activity(name));
    }

    public Activity stringToActivity(String activityName) {
        try {
            containsActivity(activityName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (Activity activity : activities){
            if (activity.getName().equals(activityName)){
                return activity;
            }
        }
        return null;
    }
    
    public boolean containsActivity(String string) {
        for (Activity activity : activities) {
            if (activity.getName().equals(string)) {
                return true;
            }
        }
        throw new IllegalArgumentException("Activity doesn't exist.");
    }

    public void setActivityExpectedHours(Activity a, int hours){
        a.setExpectedHours(hours);
    }
}
