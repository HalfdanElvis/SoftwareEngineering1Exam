package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignUserToActivitySteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;

    public AssignUserToActivitySteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("a user {string} exists")
    public void aUserExists(String string) {
        // Write code here that turns the phrase above into concrete actions
        app.addEmployee(string);
        testHelper.setUser(string);
    }

    @Given("an activity {string} exists")
    public void anActivityExists(String string) throws IllegalAccessException {
        testHelper.setProjectID(app.createProject("test").getID());
        testHelper.setActivityName(string);
        app.addActivity(testHelper.getProjectID(),string);
    }

    @Given("the user is peak")
    public void theUserIsPeak() {
        // Write code here that turns the phrase above into concrete actions
        app.stringToEmployee(testHelper.getUser()).setPeak(true);
    }

    @Given("the activity runs from week {int} to week {int} in the year {int}")
    public void theActivityRunsFromWeekWeekInTheYear(Integer startWeek, Integer endWeek, Integer year) {
        // Write code here that turns the phrase above into concrete actions
        app.fetchActivity(testHelper.getProjectID(), testHelper.getActivityName()).setStartWeek(startWeek);
        app.fetchActivity(testHelper.getProjectID(), testHelper.getActivityName()).setEndWeek(endWeek);
        app.fetchActivity(testHelper.getProjectID(), testHelper.getActivityName()).setYear(year);
    }

    @Given("the user is not assigned the activity")
    public void theUserIsNotAssignedTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        if (app.stringToEmployee(testHelper.getUser()).isAssignedActivity(testHelper.getActivityName())) {
            app.removeUserActivity(testHelper.getUser(), testHelper.getActivityName());
        }

    }

    @Given("the user is assigned {int} activities in week {int} to week {int} in the year {int}")
    public void theUserIsAssignedActivitiesInWeekToWeekInTheYear(Integer int1, Integer int2, Integer int3, Integer int4) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Project project = app.intToProject(testHelper.getProjectID());
        for (int i = 0; i < int1; i++) {
            app.addActivity(project.getID(), testHelper.getActivityName()+i);
            project.stringToActivity(testHelper.getActivityName()).setStartWeek(int2);
            project.stringToActivity(testHelper.getActivityName()).setEndWeek(int3);
            project.stringToActivity(testHelper.getActivityName()).setYear(int4);
            app.assignUserActivity(testHelper.getUser(),testHelper.getProjectID(),testHelper.getActivityName()+i);
        }
    }

    @When("the user is assigned the activity")
    public void theUserIsAssignedTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Then("the user is assigned the activity {string}")
    public void theUserIsAssignedTheActivity(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }




}
