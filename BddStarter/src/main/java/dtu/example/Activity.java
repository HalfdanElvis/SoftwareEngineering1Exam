package dtu.example;

import java.util.List;

public class Activity {
    private String name;
    private Week startWeek;
    private Week endWeek;
    private float expectedHours;
    private List<WorkData> workData;

    public Activity(String name, List<WorkData> workData) {
        this.name = name;
        this.workData = workData;
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

}
