package dtu.example.Model;

import java.util.ArrayList;
import java.util.Calendar;
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

    public void assignLeader(Employee projectLeader, Employee requestingEmployee) {
        if (!hasProjectLeader()) {
            this.projectLeader = projectLeader;
        } else if (requestingEmployee.equals(this.projectLeader)) {
            this.projectLeader = projectLeader;
        } else {
            throw new IllegalArgumentException("Only project leader can change project leader.");
        }
    }

    public boolean hasProjectLeader() {
        if (projectLeader == null) {
            return false;
        }
        return true;
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
        throw new IllegalArgumentException("Activity does not exist");
    }
    
    public boolean containsActivity(String activityName) {
        try {
            stringToActivity(activityName);
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public void assignEmployeeToActivity(Employee employee, String activityName) {
        Activity activity = stringToActivity(activityName);
        employee.assignActivity(activity);
    }

    public void setActivitiyStartAndEndWeek(String activityName, int startYear, int startWeek, int endYear, int endWeek) {
        Activity activity = stringToActivity(activityName);
        activity.setStartAndEndWeek(startYear, startWeek, endYear, endWeek);
    }

    public void logHours(String activityName, Calendar date, float hours, String employee) {
        Activity activity = stringToActivity(activityName);
        activity.logHours(date, hours, employee);
    }

    public float getUserLoggedHoursInActivityOnDate(String activityName, String username, Calendar date) {
        Activity activity = stringToActivity(activityName);
        return activity.getUserLoggedHoursOnDate(username, date);
    }

    public float[] generateReport(Employee requestingEmployee) throws IllegalAccessException {
        if (!requestingEmployee.equals(projectLeader)) {
            throw new IllegalAccessException("Only project leader can generate report");
        }
        float[] sum = {0,0};
        for (Activity activity : activities) {
            sum[0] += activity.getTotalWorkedHours();
            sum[1] += activity.getExpectedHours();
        }
        return sum;
    }

    public String getProjectLeaderName() {
        return projectLeader.getUsername();
    }

    public void removeActivity(String activityName, Employee requestingEmployee) throws IllegalAccessException {
        if (projectLeader != null && !requestingEmployee.equals(projectLeader)) {
            throw new IllegalAccessException("Only the project leader can remove an activity");
        }
        activities.remove(stringToActivity(activityName));
    }

}
