package dtu.example;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarHelper {
    private Calendar date;
    Calendar calendar = new GregorianCalendar();

    public CalendarHelper(){
    }
    
    public int getWeek(Calendar c){   

        int weekNumber=c.get(Calendar.WEEK_OF_YEAR);
        return weekNumber;
    }


    public Calendar getDate(int week, int year, int dayOfWeek){
        
        if (dayOfWeek == 0){
            dayOfWeek = 1;
        }
        if (year == 0){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        Calendar c = new GregorianCalendar();
        c.setWeekDate(year, week, dayOfWeek);
        return c;
    }
}
