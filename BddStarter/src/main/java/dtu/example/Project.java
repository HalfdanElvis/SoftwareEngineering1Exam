package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int ID;
    private List<Activity> activities = new ArrayList<>();
    private Employee projectLeader;

    public Project (String name, int ID){
        this.name = name;
        this.ID = ID;
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

    public void assignLeader(Employee projectLeader, Employee requestingEmployee) {
        if (!hasProjectLeader()) {
            this.projectLeader = projectLeader;
        } else if (requestingEmployee.equals(this.projectLeader)) {
            this.projectLeader = projectLeader;
        } else {
            throw new IllegalArgumentException("Only project leader can change project leader.");
        }
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

    public void createActivity(String name, Employee requestingEmployee) throws IllegalAccessException { 
        if (containsActivity(name)) {
            throw new IllegalArgumentException("Activity already exists in project");
        }
        if (projectLeader != null && !requestingEmployee.equals(projectLeader)) {
            throw new IllegalArgumentException("Only the project leader can create an activity");
        }
        activities.add(new Activity(name));
    }

    public Activity stringToActivity(String activityName) {
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
        return false;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivityExpectedHours(String activity, float hours, Employee requestingEmployee) throws IllegalAccessException{
        if (!requestingEmployee.equals(projectLeader)) {
            throw new IllegalAccessException("Only the project leader can set the activity's expected work hours");
        }
        stringToActivity(activity).setExpectedHours(hours);
    }

    public float getActivityExpectedHours(String activity){
        return stringToActivity(activity).getExpectedHours();
    }

}
