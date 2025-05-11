package dtu.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dtu.example.Controller.App;
import dtu.example.Utility.CalendarHelper;
import dtu.example.dto.ProjectInfo;

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
        ProjectInfo project = app.getProjectInfo(testHelper.getProjectID());
        assertEquals(hours, ProjectTestHelper.getUserTotalLoggedHours(project, testHelper.getActivityName(), testHelper.getUser()), 0);
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
