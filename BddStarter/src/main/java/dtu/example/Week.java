package dtu.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Week {
    private int year;
    private int week;

    public Week(int year, int week) {
        this.year = year;
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public int getWeek() {
        return week;
    }
    
    public boolean equals(Week otherWeek) {
        return year == otherWeek.year && week == otherWeek.week;
    }

    public boolean isGreaterOrEqual(Week otherWeek) {
        if (year > otherWeek.year) {
            return true;
        }
        return year == otherWeek.year && week >= otherWeek.week;
    }

    public boolean isLessOrEqual(Week otherWeek) {
        if (year < otherWeek.year) {
            return true;
        }
        return year == otherWeek.year && week >= otherWeek.week;
    }
    
}
