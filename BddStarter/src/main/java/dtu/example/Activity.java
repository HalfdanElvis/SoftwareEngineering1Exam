package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;

    private int year;
    private int expectedWeeks;
    private int startWeek;
    private int endWeek;
    private float expectedHours;

    public Activity(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getActiveWeeks() {
        List<Integer> aw = new ArrayList<>();
        for (int i = startWeek; i != (endWeek + 1); i = (i % 52 + 1)){
            aw.add(i);
        }
        return aw;
    }

    public int getExpectedWeeks() {
        return expectedWeeks;
    }

    public void setExpectedWeeks(int expectedWeeks) {
        this.expectedWeeks = expectedWeeks;
    }

    public float getExpectedHours() {
        return expectedHours;
    }

    public void setExpectedHours(float expectedHours) {
        this.expectedHours = expectedHours;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getStartWeek() {
        return startWeek;
    }

}
