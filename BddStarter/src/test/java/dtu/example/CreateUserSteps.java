package dtu.example;

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

        if (app.employeeExists(username)){
            app.setSignedInEmployee(username);
        } else {
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
        app.employeeExists(string);
    }
}
