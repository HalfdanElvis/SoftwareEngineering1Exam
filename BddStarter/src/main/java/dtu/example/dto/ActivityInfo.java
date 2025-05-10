package dtu.example.dto;

import java.util.List;

import dtu.example.Model.Activity;
import dtu.example.Model.Week;
import dtu.example.Model.WorkData;

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
        this.expectedHours = activity.getExpectedHours();
        this.workDataList = activity.getWorkDataList();
    }
    
    public String getName() { return name; }

    public Week getStartWeek() { return startWeek; }
    
    public Week getEndWeek() { return endWeek; }
    
    public float getExpectedHours() { return expectedHours; }
    
    public List<WorkData> getWorkDatas() { return workDataList; }
}
