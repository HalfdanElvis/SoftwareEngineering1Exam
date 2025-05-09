package dtu.example.DTO;

import java.util.List;

import dtu.example.Activity;
import dtu.example.Week;
import dtu.example.WorkData;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityInfo {
    private String name;
    private Week startWeek;
    private Week endWeek;
    private float expectedHours;
    private List<WorkData> workDataList;
    
    // Constructor
    public ActivityInfo(Activity activity) {
        this.name = activity.getName();
        this.startWeek = activity.getStartWeek();
        this.endWeek = activity.getEndWeek();
        expectedHours = activity.getExpectedHours();
        workDataList = activity.getWorkDataList();
    }

    public ActivityInfo(String name){
        this.name = name;
    }

    public ActivityInfo(String name, Week startWeek, Week endWeek, float expectedHours, List<WorkData> workDatas) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.expectedHours = expectedHours;
        this.workDataList = workDatas;
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
    
    public List<WorkData> getWorkDataList() { return workDataList; }
    public void setWorkDataList(List<WorkData> workDatas) { this.workDataList = workDatas; }
    


}
