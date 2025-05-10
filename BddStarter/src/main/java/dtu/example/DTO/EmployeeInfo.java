package dtu.example.dto;

import java.util.ArrayList;
import java.util.List;
<<<<<<< Updated upstream
import dtu.example.*;
=======

import dtu.example.Employee;
>>>>>>> Stashed changes

public class EmployeeInfo {
    private String username;
    private boolean peak = false;
    private List<ActivityInfo> activities = new ArrayList<>();
    private List<SpecialActivity> specialActivities = new ArrayList<>();

    public EmployeeInfo(Employee employee){
        this.username = employee.getUsername();
        this.peak = employee.isPeak();
        for (Activity activity : employee.getActivities()) {
            activities.add(new ActivityInfo(activity));
        }
        specialActivities = employee.getSpecialActivities();
    }

    public String getName() {
        return username;
    }

    public boolean isPeak(){
        return peak;
    }

    public List<ActivityInfo> getActivityInfos(){
        return activities;
    }

    public List<SpecialActivity> getSpecialActivities(){
        return specialActivities;
    }
}
