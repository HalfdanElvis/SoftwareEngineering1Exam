package dtu.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {
    
    private String username;
    private boolean peak = false;
    private List<Activity> activities = new ArrayList<>();

    
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
            throw new IllegalArgumentException("Special activities cannot overlap with other activities");
        }
        SpecialActivity activity = new SpecialActivity(activityName);
        activity.setStartAndEndWeek(startYear, startWeek, endYear, endWeek);
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
    public Activity getActivity(String activityName){
        Activity activity = null;
        for (int i =0; i<activities.size();i++){
            if(activities.get(i).getName() == activityName){
                activity = activities.get(i);
            }
        }
        return activity;
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
        List<Week> weeks = Week.range(startWeek, endWeek);
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
                for (Week weekInActivity : Week.range(activity.getStartWeek(), activity.getEndWeek())) {
                    if (week.equals(weekInActivity)) {
                        if (activity instanceof SpecialActivity) {
                            return false;
                        }
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
        List<Week> weeks = Week.range(startWeek, endWeek);
        for (Week week : weeks) {
            if (!isAvailableSpecial(week)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isAvailableSpecial(Week week) {
        for (Activity activity : activities) {
            if (activity.getStartWeek() != null && activity.getEndWeek() != null) {
                for (Week weekInActivity : Week.range(activity.getStartWeek(), activity.getEndWeek())) {
                    if (week.equals(weekInActivity)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }





    // Special Activity metoder

    public ArrayList<Activity> getAllSpecialActivities(){
        ArrayList<Activity> specialActivities = new ArrayList<>();
        for (Activity a : activities){
            if(a instanceof SpecialActivity) {
                specialActivities.add(a);
            }
        }
        return specialActivities;
    }

    public int howManySpecialActivities() {
        return getAllSpecialActivities().size();
    }
    
    public void printAllSpecialActivities() {

        ArrayList<Activity> specialActivities = getAllSpecialActivities();

        //SortByDate(specialActivities):

        for (int i = 1; i-1 < howManySpecialActivities(); i++){
            System.out.println(i+": "+specialActivities.get(i-1).getName()+" - stating in year: " + specialActivities.get(i-1).getStartWeek().getYear() 
            + " week: " + specialActivities.get(i-1).getStartWeek().getWeek() + " to year: " + specialActivities.get(i-1).getEndWeek().getYear() 
            + " week: " + specialActivities.get(i-1).getEndWeek().getWeek());
        }
    }

    public Activity selectSpecialActivityNumber(int index) {
        ArrayList<Activity> specialActivities = getAllSpecialActivities();

        //SortByDate(specialActivities):

        return specialActivities.get(index-1);

    }    


}
