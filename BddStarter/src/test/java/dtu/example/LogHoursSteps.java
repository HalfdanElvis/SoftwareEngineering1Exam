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
    Employee employee;
    WorkData workData;

    public LogHoursSteps(App app, TestHelper testHelper, ErrorMessageHolder errorMessageHolder, Employee employee, WorkData workData){
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
        this.employee = employee;
        this.workData = workData;
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

    @Given("the user has logged {int} hours in the activity")
    public void theUserHasYetToLogHoursInActivity(Employee employee, String activityName, int hoursInActivity ) {
        if (employee.isAssignedActivity(activityName) == true){
            assertEquals(0,hoursInActivity);
        }
        else{
            errorMessageHolder.setErrorMessage("Employee not assigned to activity");
            System.out.println(errorMessageHolder.getErrorMessage());
        }
    
        throw new io.cucumber.java.PendingException();
    }

@When("the user logs {int} hours in the activity on the date {string}")
public void theUserLogsHoursInTheActivityOnTheDate(int hours, String date) {

    workData.addHours(hours);
    throw new io.cucumber.java.PendingException();
}
@Then("the user has logged {int} hours in the activity")
public void theUserHasLoggedHoursInTheActivity(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Then("on the date {string} the user has logged {int} hours in the activity")
public void onTheDateTheUserHasLoggedHoursInTheActivity(String string, Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}


}
