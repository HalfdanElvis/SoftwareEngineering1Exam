package dtu.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class CalenderSteps {
    Calendar date = new GregorianCalendar();
    private CalendarHelper ch = new CalendarHelper();
    App app = TestHelper.app;
    Activity act;
    

    @Given("a date {string} is given")
    public void dateGiven(String dateString) throws Exception {
        // Eksempel: "2025-05-05"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = Calendar.getInstance();
        date.setTime(sdf.parse(dateString));
    }

    @Then("the week number should be {int}")
    public void returnWeek(int expectedWeek) {
        int actualWeek = ch.getWeek(date);
        assertEquals(expectedWeek, actualWeek);
    }
}
