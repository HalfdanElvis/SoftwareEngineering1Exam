package dtu.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateProjectSteps {
    
    App app = TestHelper.app;

    @Given("that a user {string} is logged in")
    public void thatAUserIsLoggedIn(String string) {
        app.setSignedInEmployee(new Employee(string));
    }
    @Given("that there are {int} projects in year {int}")
    public void thatThereAreProjectsInYear(Integer projectCount, Integer year) {
        app.setYear(year);
        for (int i = 0; i < projectCount; i++) {
            app.createProject("test");
        }
        
    }
    @Given("the current year is {int}")
    public void theCurrentYearIs(Integer year) {
        app.setYear(year);
    }
    @When("the user creates a project with name {string}")
    public void theUserCreatesAProjectWithName(String name) {
        app.createProject(name);
    }

    @Then("a project with name {string} and ID {int} exists")
    public void aProjectWithNameAndIDExists(String name, Integer id) {
        app.projectExists(name, id);
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String string) {
        //IDK HOW TO WRITE THIS
    }
}
