package dtu.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarHelper {
    
    public static Calendar parseStringAsCalendar(String dateAsString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse(dateAsString));
        return date;
    }

    public static List<Week> range(Week startWeek, Week endWeek) {

        // Precondition:
        //assert endWeek.isGreaterOrEqual(startWeek) : "Start week has to be before end week.";
        if (!endWeek.isGreaterOrEqual(startWeek)) {
            throw new IllegalArgumentException("Start week must be before end week.");
        }

        List<Week> weeks = new ArrayList<>();
        int currentYear = startWeek.getYear();
        int currentWeek = startWeek.getWeek();
        int weeksInCurrentYear = getWeeksInYear(currentYear);

        while (currentYear < endWeek.getYear() || (currentYear == endWeek.getYear() && currentWeek <= endWeek.getWeek())) {
            weeks.add(new Week(currentYear, currentWeek));
            currentWeek++;
            if (currentWeek > weeksInCurrentYear) {
                currentWeek = 1;
                currentYear++;
                weeksInCurrentYear = getWeeksInYear(currentYear);
            }
        }
        return weeks;
    }

    public static int getWeeksInYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }
}
