package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateUserSteps {

    App app;
    ErrorMessageHolder errorMessageHolder;
    
    public CreateUserSteps(App app, ErrorMessageHolder errorMessageHolder) {
        this.app = app;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("that user {string} is logged in")
    public void thatUserIsLoggedIn(String string) {
        TestHelper.username = string;
        String username = TestHelper.username;

        if (app.employeeExists(username)){
            app.setSignedInEmployee(username);
        } else {
            app.addEmployee(username);
            app.setSignedInEmployee(username);
        }

        TestHelper.app = app;
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
