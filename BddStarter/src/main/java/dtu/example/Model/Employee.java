package dtu.example.Model;

import java.util.ArrayList;
import java.util.List;

import dtu.example.Utility.CalendarHelper;

public class Employee {
    
    private String username;
    private boolean peak = false;
    private List<Activity> activities = new ArrayList<>();
    private List<SpecialActivity> specialActivities = new ArrayList<>();

    
    public Employee(String username) {
        this.username = username;
    }

    public void assignActivity(Activity activity) {
        if (activities.contains(activity)) {
            throw new IllegalArgumentException("User is already part of the activity");
        }
        if (!isAvailable(activity.getStartWeek(), activity.getEndWeek())) {
            throw new IllegalArgumentException("User has too many activities");
        }
        activities.add(activity);
    }
    
    public void assignSpecialActivity(String activityName, int startYear, int startWeek, int endYear, int endWeek) {
        Week sWeek = new Week(startYear, startWeek);
        Week eWeek = new Week(endYear, endWeek);
        if (!isAvailableSpecial(sWeek, eWeek)) {
            throw new IllegalArgumentException("Special activities cannot overlap with other special activities");
        }
        SpecialActivity activity = new SpecialActivity(activityName, startYear, startWeek, endYear, endWeek);
        specialActivities.add(activity);
    }    

    public void deleteSpecialActivity(String activityName) {
        boolean activityExists = false;
        for (int i = 0; i < specialActivities.size(); i++) {
            if (specialActivities.get(i).getName().equals(activityName) ) {
                specialActivities.remove(i);
                activityExists = true;
            }
        }
        if (!activityExists) {
            throw new IllegalArgumentException("User is not assigned that activity");
        }
    }

    public List<SpecialActivity> getSpecialActivities(){
        return specialActivities;
    }

    public boolean isAssignedActivity(String activityName) {
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }

        public boolean isAssignedSpecialActivity(String activityName) {
        for (SpecialActivity specialActivity : specialActivities) {
            if (specialActivity.getName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }

    
    

    public String getUsername() { return username; }
    public void setPeak(boolean peak) { this.peak = peak; }
    public boolean isPeak() { return peak; }

    public void removeActivity(String activityName) {
        boolean activityExists = false;
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getName().equals(activityName) ) {
                activities.remove(i);
                activityExists = true;
            }
        }
        if (!activityExists) {
            throw new IllegalArgumentException("User is not assigned that activity");
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean isAvailable(int startYear, int startWeek, int endYear, int endWeek) {
        Week sWeek = new Week(startYear, startWeek);
        Week eWeek = new Week(endYear, endWeek);
        return isAvailable(sWeek, eWeek);
    }

    public boolean isAvailable(int year, int week) {
        Week w = new Week(year, week);
        return isAvailable(w);
    }
    
    private boolean isAvailable(Week startWeek, Week endWeek) {
        List<Week> weeks = CalendarHelper.range(startWeek, endWeek);
        for (Week week : weeks) {
            if (!isAvailable(week)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isAvailable(Week week) {
        int activityCount = 0;
        for (Activity activity : activities) {
            if (activity.getStartWeek() != null && activity.getEndWeek() != null) {
                for (Week weekInActivity : CalendarHelper.range(activity.getStartWeek(), activity.getEndWeek())) {
                    if (week.equals(weekInActivity)) {
                        activityCount++;
                    }
                }
            }
        }
        if ((activityCount >= 10 && !isPeak()) || activityCount >= 20) {
            return false;
        }
        return true;
    }

    private boolean isAvailableSpecial(Week startWeek, Week endWeek) {
        List<Week> weeks = CalendarHelper.range(startWeek, endWeek);
        for (Week week : weeks) {
            if (!isAvailableSpecial(week)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isAvailableSpecial(Week week) {
        for (SpecialActivity activity : specialActivities) {
            if (activity.getStartWeek() != null && activity.getEndWeek() != null) {
                for (Week weekInActivity : CalendarHelper.range(activity.getStartWeek(), activity.getEndWeek())) {
                    if (week.equals(weekInActivity)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Special Activity metoder

    
    /*
    

    
    */

}
