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
        if (activity.getStartWeek() != null && activity.getEndWeek() != null) {
            if (!isAvailable(activity.getStartWeek(), activity.getEndWeek())) {
                throw new IllegalArgumentException("User has too many activities");
            }
        }
        activities.add(activity);
    }
    
    public void assignSpecialActivity(String activityName, int startYear, int startWeek, int endYear, int endWeek) {
        Week sWeek = new Week(startYear, startWeek);
        Week eWeek = new Week(endYear, endWeek);
        if (!isAvailableSpecial(sWeek, eWeek)) {
            throw new IllegalArgumentException("Special activities cannot overlap with other special activities");
        }
        SpecialActivity specialActivity = new SpecialActivity(activityName);
        specialActivity.setStartAndEndWeek(startYear, startWeek, endYear, endWeek);
        specialActivities.add(specialActivity);
    }    

    public List<SpecialActivity> getSpecialActivities(){
        return specialActivities;
    }

    public String getUsername() { return username; }
    public void setPeak(boolean peak) { this.peak = peak; }
    public boolean isPeak() { return peak; }

    public void removeActivity(String activityName) {
        Activity toBeRemoved = null;
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                toBeRemoved = activity;
            }
        }
        if (toBeRemoved == null) {
            throw new IllegalArgumentException("User is not assigned that activity");
        }
        activities.remove(toBeRemoved);
    }

    public void removeSpecialActivity(String specialActivityName) {
        SpecialActivity toBeRemoved = null;
        for (SpecialActivity specialActivity : specialActivities) {
            if (specialActivity.getName().equals(specialActivityName)) {
                toBeRemoved = specialActivity;
            }
        }
        
        if (toBeRemoved == null) {
            throw new IllegalArgumentException("User is not assigned that special activity");
        }
        specialActivities.remove(toBeRemoved);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean isAvailable(int startYear, int startWeek, int endYear, int endWeek) {
        Week sWeek = new Week(startYear, startWeek);
        Week eWeek = new Week(endYear, endWeek);
        return isAvailable(sWeek, eWeek);
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
        for (SpecialActivity sa : specialActivities){
            if (sa.getStartWeek() != null && sa.getEndWeek() != null) {
                for (Week weekInActivity : CalendarHelper.range(sa.getStartWeek(), sa.getEndWeek())) {
                    if (week.equals(weekInActivity)) {
                        return false;
                    }
                }
            }
        }
        
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

}
