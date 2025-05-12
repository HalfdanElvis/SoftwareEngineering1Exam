package dtu.example.Utility;

import dtu.example.Model.Week;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//Simon
public class CalendarHelper {
    //Simon
    public static Calendar parseStringAsCalendar(String dateAsString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar date = Calendar.getInstance();
        sdf.setLenient(false);
        date.setTime(sdf.parse(dateAsString));
        return date;
    }
    //Simon
    public static List<Week> range(Week startWeek, Week endWeek) {

        // Precondition:
        assert endWeek.isGreaterOrEqual(startWeek): "Start week has to be before end week.";   // 1

        List<Week> weeks = new ArrayList<>();
        int currentYear = startWeek.getYear();
        int currentWeek = startWeek.getWeek();
        int weeksInCurrentYear = getWeeksInYear(currentYear);

        while (currentYear < endWeek.getYear() ||               
            (currentYear == endWeek.getYear() &&
            currentWeek <= endWeek.getWeek())) {   // 2
            weeks.add(new Week(currentYear, currentWeek));       
            currentWeek++;
            if (currentWeek > weeksInCurrentYear) {   // 3
                currentWeek = 1;
                currentYear++;
                weeksInCurrentYear = getWeeksInYear(currentYear);
            }
        }
        
        // Postcondition:
        Week prevWeek = startWeek;
        int i = 0;
        for (Week w : weeks) {
            if (i == 0) {
                assert w.equals(startWeek);
            } else if (i == weeks.size()) {
                assert w.equals(endWeek);
            } else {
                assert (w.equals(new Week(prevWeek.getYear(), prevWeek.getWeek()+1)) || w.equals(new Week(prevWeek.getYear()+1, 1)));
            }
            prevWeek = w;
            i++;
        }
        
        return weeks;   // 4
    }
    //Marcus
    public static int getWeeksInYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }
}
