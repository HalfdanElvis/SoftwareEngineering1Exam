package dtu.example.dto;

import java.util.List;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityInfo {
    private String name;
    private Week startWeek;
    private Week endWeek;
    private float expectedHours;
    private List<WorkData> workDatas;
    
    // Constructors 
    public ActivityInfo(Activity a) {
        this.name = a.getName();
        this.startWeek = a.getStartWeek();
        this.endWeek = a.getEndWeek();
        expectedHours = a.getExpectedHours();
        workDatas = a.getWorkDatas();
    }

    public ActivityInfo(String name){
        this.name = name;
    }

    public ActivityInfo(String name, Week startWeek, Week endWeek, float expectedHours, List<WorkData> workDatas) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.expectedHours = expectedHours;
        this.workDatas = workDatas;
    }

    // Getters & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Week getStartWeek() { return startWeek; }
    public void setStartWeek(Week startWeek) { this.startWeek = startWeek; }
    
    public Week getEndWeek() { return endWeek; }
    public void setEndWeek(Week endWeek) { this.endWeek = endWeek; }
    
    public float getExpectedHours() { return expectedHours; }
    public void setExpectedHours(float expectedHours) { this.expectedHours = expectedHours; }
    
    public List<WorkData> getWorkDatas() { return workDatas; }
    public void setWorkDatas(List<WorkData> workDatas) { this.workDatas = workDatas; }
    


}
