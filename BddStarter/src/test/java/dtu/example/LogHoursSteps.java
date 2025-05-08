package dtu.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class LogHoursSteps {
    Calendar date = new GregorianCalendar();
    private CalendarHelper ch = new CalendarHelper();
    //App app = TestHelper.app;
    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;

    public LogHoursSteps(App app, TestHelper testHelper, ErrorMessageHolder errorMessageHolder){
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("that a user {string} is logged in")
    public void thatAUserIsLoggedIn(String username) {
        testHelper.setUser(username);

        if (app.employeeExists(username)){
            app.setSignedInEmployee(username);
        } else {
            app.addEmployee(username);
            app.setSignedInEmployee(username);
        }
    }

    
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

    @Given("the user has logged {int} hours in the activity initially")
    public void theUserHasYetToLogHoursInActivity(String activityName, int hoursInActivity ) {
        String employee=testHelper.getUser();
        if (employee.isAssignedActivity(activityName) == true){
            assertEquals(0,hoursInActivity);
        }
        else{
            errorMessageHolder.setErrorMessage("Employee not assigned to activity");
            System.out.println(errorMessageHolder.getErrorMessage());
        }
    
        throw new io.cucumber.java.PendingException();
    }

@When("the user logs {float} hours in the activity on the date {string}")
public void theUserLogsHoursInTheActivityOnTheDate(float hours, Calendar date, Employee employee) {
    WorkData data=workData.makeWorkData(date, employee, hours);
    activity.addWorkData(data);
    throw new io.cucumber.java.PendingException();
}
@Then("the user has logged {int} hours in the activity")
public void theUserHasLoggedHoursInTheActivity(float expectedHours, Employee employee) {
    assertEquals(expectedHours, activity.getEmployeeTotalHours(employee), 0.01);
    throw new io.cucumber.java.PendingException();
}
@Then("on the date {string} the user has logged {float} hours in the activity")
public void onTheDateTheUserHasLoggedHoursInTheActivity(Employee employee, float expectedHours, Calendar date) {
    assertEquals(expectedHours, activity.getEmployeeHoursOnDate(employee, date), 0.01);
    throw new io.cucumber.java.PendingException();
}


}
