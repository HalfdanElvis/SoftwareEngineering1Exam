package dtu.example;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Test;

public class ActivityExpectedHoursSteps {

    private App app;
    private ErrorMessageHolder errorMessageHolder;
    
    private TestHelper testHelper;
    private String projectLeaderName;


    public ActivityExpectedHoursSteps (App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }


    
    @Given("that there exists a project with id {int} and name {string}")
    public void thatThereExistsAProjectWithIdAndName(Integer id, String string) {
        testHelper.setProjectID(id);
        app.createProject(string).setID(testHelper.getProjectID());

    }

    @Given("the project with id {int} contains an activity with name {string}")
    public void theProjectWithIdContainsAnActivityWithName(Integer id, String activityName) {
        try {
            testHelper.setActivityName(activityName);
            app.addActivity(testHelper.getProjectID(), activityName);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the user {string} is the project leader")
    public void theUserIsTheProjectLeader(String username) {
        if (!app.employeeExists(username)) {
            app.addEmployee(username);
        }
        app.assignLeader(username, testHelper.getProjectID());
        projectLeaderName = username;
    }

    
    @Given("the activity's expected total work hours is already {int}")
    public void theActivitySExpectedTotalWorkHoursIsAlready(Integer hours) {
        app.setSignedInEmployee(projectLeaderName);
        try {
            app.setActivityExpectedHours(testHelper.getProjectID(), testHelper.getActivityName(), hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the user {string} sets the activity's expected total work hours to {int}")
    public void theUserSetsTheActivitySExpectedTotalWorkHoursTo(String user, Integer hours) {
        app.setSignedInEmployee(user);
        try {
            app.setActivityExpectedHours(testHelper.getProjectID(), testHelper.getActivityName(), hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity's expected total work hours is {int}")
    public void theActivitySExpectedTotalWorkHoursIs(Integer hours) {
        assertEquals(hours, app.getActivityExpectedHours(testHelper.getProjectID(), testHelper.getActivityName()), 0);
    }
}
