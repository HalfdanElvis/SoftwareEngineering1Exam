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

    @Override
    public String toString() {
        return year + ", " + week;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, week);
    }

    public static List<Week> range(Week startWeek, Week endWeek) {
        List<Week> weeks = new ArrayList<>();
        int currentYear = startWeek.year;
        int currentWeek = startWeek.week;
        int weeksInCurrentYear = getWeeksinYear(currentYear);

        while (currentYear < endWeek.year || (currentYear == endWeek.year && currentWeek <= endWeek.week)) {
            weeks.add(new Week(currentYear, currentWeek));
            currentWeek++;
            if (currentWeek > weeksInCurrentYear) {
                currentWeek = 1;
                currentYear++;
                weeksInCurrentYear = getWeeksinYear(currentYear);
            }
        }
        return weeks;
    }

    public static int getWeeksinYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }
    
}
