package dtu.example.dto;

import java.util.ArrayList;
import java.util.List;
import dtu.example.Model.Activity;
import dtu.example.Model.Employee;
import dtu.example.Model.SpecialActivity;

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
