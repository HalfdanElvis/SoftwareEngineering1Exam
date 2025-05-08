package dtu.example;

import java.util.Calendar;

public class WorkData {
    private Calendar date;
    private String employee;
    private float hours;

    public WorkData(Calendar date, String employee, float hours) {
        this.date = date;
        this.employee = employee;
        this.hours = hours;
    }

    public Calendar getDate() {
        return date;
    }

    public String getEmployee() {
        return employee;
    }

    public float getHours() {
        return hours;
    }
    
    public void addHours(float hours) {
        this.hours += hours;
    }
}
