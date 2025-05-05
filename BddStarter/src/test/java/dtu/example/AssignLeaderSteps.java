package dtu.example;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignLeaderSteps {
    App app = TestHelper.app;
    int projectID;

    @Given("a project with ID {int} exists")
    public void aProjectWithIDExists(Integer id) {
        // Write code here that turns the phrase above into concrete actions
        app.createProject("test");
        projectID = id;

    }
    @When("the user assigns an employee with intials {string} as project leader for the project with ID {int}")
    public void theUserAssignsAnEmployeeWithIntialsAsProjectLeaderForTheProjectWithID(String username, Integer id) {
        // Write code here that turns the phrase above into concrete actions
        app.assignLeader(username, projectID);
    }
    @Then("the project has a project leader {string}")
    public void theProjectHasAProjectLeader(String string) {
        // Write code here that turns the phrase above into concrete actions
        assert(app.getProjectLeaderName(projectID).equals(string));
    }
}
