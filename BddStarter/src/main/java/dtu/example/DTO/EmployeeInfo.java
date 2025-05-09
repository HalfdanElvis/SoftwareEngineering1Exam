package dtu.example.DTO;

import java.util.ArrayList;
import java.util.List;
import dtu.example.*;
import io.cucumber.java.lu.a;

public class EmployeeInfo {
    private String username;
    private boolean peak = false;
    private List<ActivityInfo> activityDTOs = new ArrayList<>();

    public EmployeeInfo(String username, boolean peak, List<ActivityInfo> activityDTOs){
        this.username = username;
        this.peak = peak; 
        this.activityDTOs = activityDTOs;
    }

    public List<ActivityInfo> getActivities(){
        return activityDTOs;
    }
    public void addActivityInfo(ActivityInfo act){
        activityDTOs.add(act);
    }
    public boolean isPeak(){

        return peak;
    }
    public void setPeak(boolean c){
        this.peak=c;
    }
    
    public ActivityInfo getActivity(String activityName){
        ActivityInfo activity = null;
        for (int i =0; i<activityDTOs.size();i++){
            if(activityDTOs.get(i).getName().equals(activityName)){
                activity = activityDTOs.get(i);
            }
        }
        return activity;
    }
    public void removeActivity(String activityName){
        for (int i = 0; i<activityDTOs.size();i++){
            if (activityDTOs.get(i).getName().equals(activityName)){
                activityDTOs.remove(i);
            }
        }
    }

}
