package dtu.example;

import java.util.List;

import dtu.example.Model.Week;

public class TestHelper {
    
    private String user;
    private int projectID;
    private String activityName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public static boolean areWeekRangesEqual(List<Week> range1, List<Week> range2){
        // Check if sizes match first
        if (range1.size() != range2.size()) {
            return false;
        }

        // Compare each corresponding week
        for (int i = 0; i < range1.size(); i++) {
            Week week1 = range1.get(i);
            Week week2 = range2.get(i);
            
            if (week1.getYear() != week2.getYear() || week1.getWeek() != week2.getWeek()) {
                return false; // Exit early on mismatch
            }
        }
        return true;
    }

}
