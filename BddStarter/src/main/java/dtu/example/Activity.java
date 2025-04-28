package dtu.example;

public class Activity {
    private String name;

    private int[] activeWeeks;
    private int expectedWeeks;
    private float expectedHours;

    public Activity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int[] getActiveWeeks() {
        return activeWeeks;
    }

    public void setActiveWeeks(int[] activeWeeks) {
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
