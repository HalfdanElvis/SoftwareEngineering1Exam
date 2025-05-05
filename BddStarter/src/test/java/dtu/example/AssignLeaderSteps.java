package dtu.example;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignLeaderSteps {
    App app;
    int projectID;

    public AssignLeaderSteps(App app) {
        this.app = app;
    }

    @Given("a project with ID {int} exists")
    public void aProjectWithIDExists(Integer id) {
        // Write code here that turns the phrase above into concrete actions
        app.createProject("test");
        projectID = id;

    }
    @Given("the user {string} exists in the system")
    public void theUserExistsInTheSystem(String string) {
        app.addEmployee(string);
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
