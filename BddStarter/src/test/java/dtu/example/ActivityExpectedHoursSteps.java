package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivityExpectedHoursSteps {

    private App app = new App();
    @Given("that there exists a project with id {int} and name {string}")
    public void thatThereExistsAProjectWithIdAndName(Integer int1, String string) {
        app.createProject(string).setID(int1);
    }

    @Given("the project with id {int} contains an activity with name {string}")
    public void theProjectWithIdContainsAnActivityWithName(Integer int1, String string) {
        app.intToProject(int1).

    }

    @Given("the user is the project leader")
    public void theUserIsTheProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user sets the activity's expected total work hours to {int}")
    public void theUserSetsTheActivitySExpectedTotalWorkHoursTo(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity's expected total work hours is already {int}")
    public void theActivitySExpectedTotalWorkHoursIsAlready(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user is not the project leader")
    public void theUserIsNotTheProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the activity's expected total work hours is {int}")
    public void theActivitySExpectedTotalWorkHoursIs(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
