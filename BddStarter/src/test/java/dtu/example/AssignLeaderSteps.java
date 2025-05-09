package dtu.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignLeaderSteps {
    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;

    public AssignLeaderSteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("a project with ID {int} exists")
    public void aProjectWithIDExists(Integer id) {
        testHelper.setProjectID(app.createProject("test"));

    }
    @Given("the user {string} exists in the system")
    public void theUserExistsInTheSystem(String string) {
        app.addEmployee(string);
    }
    @Given("the project has a project leader set to {string}")
    public void the_project_has_a_project_leader_set_to(String username) {
        app.assignLeader(username, testHelper.getProjectID());
    }
    @When("the user assigns an employee with initials {string} as project leader for the project with ID {int}")
    public void theUserAssignsAnEmployeeWithInitialsAsProjectLeaderForTheProjectWithID(String username, Integer id) {
        try {
            app.assignLeader(username, id);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
        
    }
    @Then("the project should have a project leader {string}")
    public void theProjectShouldHaveAProjectLeader(String string) {
        assertEquals(app.getProjectLeaderName(testHelper.getProjectID()), string);
    }
}
