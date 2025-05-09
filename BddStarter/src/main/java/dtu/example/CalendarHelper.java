package dtu.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarHelper {
    
    public static Calendar parseStringAsCalendar(String dateAsString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse(dateAsString));
        return date;
    }

    public static List<Week> range(Week startWeek, Week endWeek) {
        List<Week> weeks = new ArrayList<>();
        int currentYear = startWeek.getYear();
        int currentWeek = startWeek.getWeek();
        int weeksInCurrentYear = getWeeksinYear(currentYear);

        while (currentYear < endWeek.getYear() || (currentYear == endWeek.getYear() && currentWeek <= endWeek.getWeek())) {
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
