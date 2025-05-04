package dtu.example;

import java.util.List;

public class Activity {
    private String name;

    private List<Integer> years;
    private List<Integer> activeWeeks;
    private int expectedWeeks;
    private float expectedHours;

    public Activity(String name) {
        this.name = name;
    }

    public List<Integer> getYear() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getActiveWeeks() {
        return activeWeeks;
    }

    public void setActiveWeeks(List<Integer> activeWeeks) {
        this.activeWeeks = activeWeeks;
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

}
