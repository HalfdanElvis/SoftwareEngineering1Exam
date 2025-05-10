package dtu.example;

import static org.junit.Assert.assertEquals;

import dtu.example.Controller.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenerateReportSteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;
    float[] report;
    
    public GenerateReportSteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @When("the user generates a report")
    public void theUserGeneratesAReport() {
        try {
            report = app.generateReport(testHelper.getProjectID());
        } catch (IllegalAccessException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the activity's total worked hours is {int}")
    public void theActivitySTotalWorkedHoursIs(Integer hours) {
        try {
            app.logHours(testHelper.getProjectID(), testHelper.getActivityName(), "01-01-2024", hours);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Then("the report returned is {int} hours out of {int} hours")
    public void theReportReturnedIsHoursOutOfHours(Integer int1, Integer int2) {
        assertEquals(int1, report[0],0);
        assertEquals(int2, report[1],0);
    }

}
