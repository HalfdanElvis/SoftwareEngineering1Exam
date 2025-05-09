package dtu.example;

import java.util.Calendar;

public class DateServer {
    
    public int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
}
