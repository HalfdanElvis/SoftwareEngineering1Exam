package dtu.example;

import java.text.ParseException;
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
    
    @Given("a date {string} is given")
    public void dateGiven(String dateString) throws Exception {
        // Eksempel: "2025-05-05"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = Calendar.getInstance();
        date.setTime(sdf.parse(dateString));
    }

    @Then("the week number should be {int}")
    public void returnWeek(int expectedWeek) {
        int actualWeek = ch.getWeek(date); //Skal måske rettes ift low coupling
        assertEquals(expectedWeek, actualWeek);
    }

    @Given("the user has logged {int} hours in the activity initially")
    public void theUserHasYetToLogHoursInActivity(String activityName, int hoursInActivity ) {
        if (app.employeeIsAssignedActivity(app.getSignedInEmployeeUsername(), activityName) == true){
            assertEquals(0,hoursInActivity);
        }
        else{
            errorMessageHolder.setErrorMessage("Employee not assigned to activity");
            System.out.println(errorMessageHolder.getErrorMessage());
        }
    
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user has logged {int} hours in the activity on the date {string}")
    public void theUserHasLoggedHoursInTheActivityOnTheDate(float hours, String date) {
        try {
            app.logHours(testHelper.getProjectID(), testHelper.getActivityName(), date, hours);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @When("the user logs {float} hours in the activity on the date {string}")
    public void theUserLogsHoursInTheActivityOnTheDate(float hours, String date) {
        try {
            app.logHours(testHelper.getProjectID(), testHelper.getActivityName(), date, hours);
        } catch (Exception e) {
            // TODO: handle exception
        }
       
    }
    
    @When("the user logs {int} hours in the activity {string} on the date {string}")
    public void theUserLogsHoursInTheActivityOnTheDate(float hours, String activityName, String date) {
        try {
            app.logHours(testHelper.getProjectID(), activityName, date, hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the user removes {int} hours from the activity on the date {string}")
    public void theUserRemovesHoursFromTheActivityOnTheDate(float hours, String date) {
        try {
            app.logHours(testHelper.getProjectID(), testHelper.getActivityName(), date, -hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }


    @Then("the user should have logged {int} hours in the activity")
    public void theUserShouldHaveLoggedHoursInTheActivity(float hours) {
        assertEquals(hours, app.getUserTotalLoggedHoursInActivity(testHelper.getProjectID(), testHelper.getActivityName(), testHelper.getUser()), 0);
    }

    @Then("on the date {string} the user has logged {float} hours in the activity")
    public void onTheDateTheUserHasLoggedHoursInTheActivity(String date, float hours) {
        //assertEquals(expectedHours, activity.getEmployeeHoursOnDate(employee, date), 0.01);
        try {
            assertEquals(hours, app.getUserLoggedHoursInActivityOnDate(testHelper.getProjectID(), testHelper.getActivityName(), testHelper.getUser(), date), 0);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
