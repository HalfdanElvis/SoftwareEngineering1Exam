package dtu.example;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignLeaderSteps {
    App app;
    int projectID;
    ErrorMessageHolder errorMessageHolder;

    public AssignLeaderSteps(App app, ErrorMessageHolder errorMessageHolder) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a project with ID {int} exists")
    public void aProjectWithIDExists(Integer id) {
        // Write code here that turns the phrase above into concrete actions
        app.createProject("test");
        projectID = id;
        assert(app.intToProject(projectID) != null);

    }
    @Given("the user {string} exists in the system")
    public void theUserExistsInTheSystem(String string) {
        app.addEmployee(string);
    }
    @Given("the project has a project leader set to {string}")
    public void the_project_has_a_project_leader_set_to(String username) {
        app.assignLeader(username, projectID);
    }
    @When("the user assigns an employee with initials {string} as project leader for the project with ID {int}")
    public void theUserAssignsAnEmployeeWithInitialsAsProjectLeaderForTheProjectWithID(String username, Integer id) {
        // Write code here that turns the phrase above into concrete actions
        try {
            app.assignLeader(username, projectID);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }
    @Then("the project has a project leader {string}")
    public void theProjectHasAProjectLeader(String string) {
        // Write code here that turns the phrase above into concrete actions
        assert(app.getProjectLeaderName(projectID).equals(string));
    }
}
