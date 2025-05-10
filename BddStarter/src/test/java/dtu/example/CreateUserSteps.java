package dtu.example;

import dtu.example.dto.EmployeeInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Test;

public class CreateUserSteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    TestHelper testHelper;
    
    public CreateUserSteps(App app, ErrorMessageHolder errorMessageHolder, TestHelper testHelper) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
        this.testHelper = testHelper;
    }

    @Given("that user {string} is logged in")
    public void thatUserIsLoggedIn(String username) {
        testHelper.setUser(username);

        try {
            app.setSignedInEmployee(username);
        } catch (Exception e) {
            app.addEmployee(username);
            app.setSignedInEmployee(username);
        }
    }

    @When("the User creates a new User with name {string}")
    public void theUserCreatesANewUserWithName(String string) {
        try {
            app.addEmployee(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }


    @Then("the User {string} is successfully created")
    public void theUserIsSuccessfullyCreated(String string) {
        EmployeeInfo employee = app.getEmployeeInfo(string);
        assert(employee.getName().equals(string));
    }
}
