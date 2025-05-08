package dtu.example;

import java.util.Calendar;

public class WorkData {
    private Calendar date;
    private Employee employee;
    private float hours;
    private Activity activity;

    public WorkData(Calendar date, Employee employee, float hours) {
        this.date = date;
        this.employee = employee;
        this.hours = hours;
    }

    public Calendar getDate() {
        return date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public float getHours() {
        return hours;
    }
    
    public void addHours(float hours) {
        this.hours += hours;
    }
}
