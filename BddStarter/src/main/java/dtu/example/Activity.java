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
    //Check if this one is used by the end
    /* 
    public List<WorkData> getEmployeeWorkData(Employee employee){
        for (int i=0;i<workDatas.size(); i++){
            WorkData data=workDatas.get(i);
            //if (data.getEmployee() == employee){
             //   employeeData.add(data);
            //}
        }
        return employeeData;
    }
    
    public float getEmployeeTotalHoursOnActivity(Employee employee, String activityName){
        List<WorkData> datas=getEmployeeWorkData(employee);
        float totalHours = 0;
        for(int i =0; i<datas.size(); i++){
            totalHours+=datas.get(i).getHours();
        }
        return totalHours;
    }
    public float getEmployeeHoursOnDate(Employee employee, Calendar date){
        List<WorkData> datas=getEmployeeWorkData(employee);
        float hours = 0;
        for (int i =0; i<datas.size(); i++){
            if (datas.get(i).getDate() == date){
                hours=datas.get(i).getHours();
            }
        }
        return hours;
    }
    */

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

    
    /*
    public WorkData makeWorkData(Calendar date, Employee employee, float hours){
        WorkData workData = new WorkData(date, employee, hours);
        return workData;
    }*/

}
