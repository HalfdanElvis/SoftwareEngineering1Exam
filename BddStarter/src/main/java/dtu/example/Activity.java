package dtu.example;

import java.util.List;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity {
    private String name;
    private Week startWeek;
    private Week endWeek;
    private float expectedHours;
    private List<WorkData> workDatas;

    public Activity(String name) {
        this.name = name;
        this.workDatas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Week> getActiveWeeks() {
        return Week.range(startWeek, endWeek);
    }

    public float getExpectedHours() {
        return expectedHours;
    }

    public void setExpectedHours(float expectedHours) {
        this.expectedHours = expectedHours;
    }

    public Week getEndWeek() {
        return endWeek;
    }

    public Week getStartWeek() {
        return startWeek;
    }

    public void setStartAndEndWeek(int startYear, int startWeek, int endYear, int endWeek) {
        this.startWeek = new Week(startYear, startWeek);
        this.endWeek = new Week(endYear, endWeek);
    }

    public void logHours(Calendar date, float hours, String employee) {
        if (hours == 0) {
            return;
        }
        WorkData workData = getSpecificWorkData(date, employee);
        if (workData == null) {
            workData = new WorkData(date, employee, hours);
            workDatas.add(workData);
        }
        else {
            workData.addHours(hours);
        }
        if (workData.getHours() < 0) {
            workData.addHours(-hours);
            throw new IllegalArgumentException("You haven't worked that long in this activity");
        }
        if (workData.getHours() == 0) {
            workDatas.remove(workData);
        }
    }
    
    private WorkData getSpecificWorkData(Calendar date, String employee) {
        for (WorkData workData : workDatas) {
            if (workData.getDate().equals(date) && workData.getEmployee().equals(employee)) {
                return workData;
            }
        }
        return null;
    }

    public float getUserLoggedHoursOnDate(String username, Calendar date) {
        for (WorkData workData : workDatas) {
            if (workData.getEmployee().equals(username) && workData.getDate().equals(date)) {
                return workData.getHours();
            }
        }
        return 0;
    }

    public float getUserTotalLoggedHours(String username) {
        float sum = 0;
        for (WorkData workData : workDatas) {
            if (workData.getEmployee().equals(username)) {
                sum += workData.getHours();
            }
        }
        return sum;
    }

    public float getTotalWorkedHours() {
        float sum = 0;
        for (WorkData workData : workDatas) {
            sum += workData.getHours();
        }
        return sum;
    }

}
