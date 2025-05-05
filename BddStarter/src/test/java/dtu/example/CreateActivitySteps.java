package dtu.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {

    App app = TestHelper.app;
    int projectID;

    @Given("that there exists a project {string} with project ID {int}")
    public void thatThereExistsAProjectWithProjectID(String string, Integer int1) {
        app.createProject(string);
        projectID = int1;
    }
    @Given("the project does not have a project leader")
    public void theProjectDoesNotHaveAProjectLeader() {
        assert(!app.projectHasLeader(projectID));
    }
    @When("the user creates an activity {string} in the project")
    public void theUserCreatesAnActivityInTheProject(String string) {
        app.createActivity(projectID, string);
    }
    @Then("the project contains an activity {string}")
    public void theProjectContainsAnActivity(String string) {
        assert(app.projectContainsActivity(projectID, string));
    }
    @Given("the user is assigned {int} activities in week {int} in the year {int}")
    public void theUserIsAssignedActivitiesInWeekInTheYear(Integer int1, Integer int2, Integer int3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user creates a special activity {string} in week {int} of the year {int}")
    public void theUserCreatesASpecialActivityInWeekOfTheYear(String string, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user is assigned to the special activity {string}")
    public void theUserIsAssignedToTheSpecialActivity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
