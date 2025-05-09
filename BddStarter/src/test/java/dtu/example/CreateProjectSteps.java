package dtu.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateProjectSteps {
    
    App app;
    TestHelper testHelper;
    ErrorMessageHolder errorMessageHolder;
    MockYearHolder yearHolder;

    public CreateProjectSteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper, MockYearHolder yearHolder) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
        this.yearHolder = yearHolder;
    }


    @Given("that there are {int} projects in year {int}")
    public void thatThereAreProjectsInYear(Integer projectCount, Integer year) {
        yearHolder.setYear(year);
        for (int i = 0; i < projectCount; i++) {
            app.createProject("test");
        }
        
    }
    @Given("the current year is {int}")
    public void theCurrentYearIs(Integer year) {
        yearHolder.setYear(year);
    }
    @When("the user creates a project with name {string}")
    public void theUserCreatesAProjectWithName(String name) {
        try {
            app.createProject(name);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("a project with name {string} and ID {int} exists")
    public void aProjectWithNameAndIDExists(String name, Integer id) {
        app.projectExists(name, id);
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String string) {
        assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }

    
}
