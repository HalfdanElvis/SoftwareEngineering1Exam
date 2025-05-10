package dtu.example;

import static org.junit.Assert.assertEquals;

import dtu.example.DTO.ProjectInfo;
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
        app.createProject(string);

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
        try {
            app.addEmployee(username);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(username);
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
        ProjectInfo project = app.createDTOProject(testHelper.getProjectID()); 
        assertEquals(hours, ProjectTestHelper.getActivityExpectedHours(project, testHelper.getActivityName()), 0);
    }
}
