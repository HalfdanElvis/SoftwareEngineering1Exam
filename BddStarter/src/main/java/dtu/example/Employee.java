package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    
    private String username;
    private boolean peak = false;
    private List<Activity> activities = new ArrayList<>();

    
    public Employee(String username) {
        this.username = username;
    }

    public void assignActivity(Activity activity) {
        activities.add(activity);
    }
    
    public boolean isAssignedActivity(String activityName) {
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }
    

    public String getUsername() { return username; }
    public void setPeak(boolean peak) { this.peak = peak; }
    public boolean isPeak() { return peak; }

    public int howManyAssignedActivites() {
        return activities.size();
    }

    public void removeActivity(String activityname) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getName().equals(activityname) ) {
                activities.remove(i);
            }
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }
    

    
}
