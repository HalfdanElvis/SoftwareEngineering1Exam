package dtu.example.DTO;

import java.util.ArrayList;
import java.util.List;
import dtu.example.*;

public class EmployeeDTO {
    private String username;
    private boolean peak = false;
    private List<Activity> activityDTOs = new ArrayList<>();

    public EmployeeDTO(String username, boolean peak, List<Activity> activityDTOs){
        this.username = username;
        this.peak = peak; 
        this.activityDTOs = activityDTOs;
    }
    public EmployeeDTO(Employee employee){
        this.username = employee.getUsername();
        this.peak = employee.isPeak();
        this.activityDTOs = employee.getActivities();
    }

    public List<Activity> getActivities(){
        return activityDTOs;
    }
    public boolean isPeak(){

        return peak;
    }
    
    public Activity getActivity(String activityName){
        Activity activity = null;
        for (int i =0; i<activityDTOs.size();i++){
            if(activityDTOs.get(i).getName().equals(activityName)){
                activity = activityDTOs.get(i);
            }
        }
        return activity;
    }

}
